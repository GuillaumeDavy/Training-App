package com.guillaume.training;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/drop_create_schema.sql"})
@ContextConfiguration(initializers = { ExerciceIntegrationTest.Initializer.class})
class ExerciceIntegrationTest {

    @LocalServerPort
    int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    @SneakyThrows
    public void shouldAddAnExercice() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"exercice\",\n" +
                "  \"description\": \"description\" \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/exercices")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "name", is("exercice"),
                        "description", is("description")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_exercice_script.sql"})
    @Test
    @SneakyThrows
    public void shouldUpdateAnExercice() {
        get("/exercices/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", is(1),
                        "name", is("exercice1"),
                        "description", is("description")
                );

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"exercice modifié\",\n" +
                "  \"description\": \"description modifié\" \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/exercices/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "name", is("exercice modifié"),
                        "description", is("description modifié")
                );
    }

    @Test
    @SneakyThrows
    public void shouldCreateAnExerciceIfTryingToUpdateAnNonExistingExercice() {
        get("/exercices/1")
                .then()
                .assertThat()
                .statusCode(500);

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"exercice\",\n" +
                "  \"description\": \"description\" \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/exercices/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "name", is("exercice"),
                        "description", is("description")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_exercice_script.sql"})
    @Test
    public void shouldFindAnExistingExerciceByID(){
        get("/exercices/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", is(1),
                        "name", is("exercice1"),
                        "description", is("description")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_exercice_script.sql"})
    @Test
    public void shouldFindAllExercices(){
        get("/exercices")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", hasItems(1, 2, 3),
                        "name", hasItems("exercice1", "exercice2", "exercice3"),
                        "description", hasItems("description", "description", "description")
                );
    }

    @Test
    public void shouldReturnAnInternalErrorIfExerciceDoesNotExist(){
        get("/exercices/1")
                .then()
                .assertThat()
                .statusCode(500);
    }
}