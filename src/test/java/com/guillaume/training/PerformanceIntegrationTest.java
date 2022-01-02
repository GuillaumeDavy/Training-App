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
@Sql({
        "/drop_create_schema.sql",
        "/create_default_user_script.sql",
        "/create_default_exercice_script.sql"
})
@ContextConfiguration(initializers = { PerformanceIntegrationTest.Initializer.class})
public class PerformanceIntegrationTest {

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
    public void shouldAddAPerformance() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"maxWeight\": 60.5,\n" +
                "  \"userID\": 1,\n" +
                "  \"exerciceID\": 1 \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/performances")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "maxWeight", is(60.5f),
                        "exerciceID", is(1),
                        "userID", is(1)
                );
    }

    @Sql({
            "/drop_create_schema.sql",
            "/create_default_user_script.sql",
            "/create_default_exercice_script.sql",
            "/create_default_performance_script.sql"
    })
    @Test
    @SneakyThrows
    public void shouldUpdateAPerformance() {
        get("/performances/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", is(1),
                        "maxWeight", is(50.5f),
                        "exerciceID", is(1),
                        "userID", is(1)
                );

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"maxWeight\": 60.5,\n" +
                "  \"userID\": 1,\n" +
                "  \"exerciceID\": 1 \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/performances/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "maxWeight", is(60.5f),
                        "exerciceID", is(1),
                        "userID", is(1)
                );
    }

    @Test
    @SneakyThrows
    public void shouldCreateAnPerformanceIfTryingToUpdateAnNonExistingPerformance() {
        get("/performances/1")
                .then()
                .assertThat()
                .statusCode(500);

        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"maxWeight\": 60.5,\n" +
                "  \"userID\": 1,\n" +
                "  \"exerciceID\": 1 \n}";

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/performances/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "id", is(1),
                        "maxWeight", is(60.5f),
                        "exerciceID", is(1),
                        "userID", is(1)
                );
    }

    @Sql({
            "/drop_create_schema.sql",
            "/create_default_user_script.sql",
            "/create_default_exercice_script.sql",
            "/create_default_performance_script.sql"
    })
    @Test
    public void shouldFindAnExistingPerformanceeByID(){
        get("/performances/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", is(1),
                        "maxWeight", is(50.5f),
                        "exerciceID", is(1),
                        "userID", is(1)
                );
    }

    @Sql({
            "/drop_create_schema.sql",
            "/create_default_user_script.sql",
            "/create_default_exercice_script.sql",
            "/create_default_performance_script.sql"
    })
    @Test
    public void shouldFindAllPerformances(){
        get("/performances")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", hasItems(1, 2, 3),
                        "maxWeight", hasItems(50.5f, 60f, 10f),
                        "exerciceID", hasItems(1, 2, 3),
                        "userID", hasItems(1, 2, 3)
                );
    }

    @Test
    public void shouldReturnAnInternalErrorIfPerformanceDoesNotExist(){
        get("/performances/1")
                .then()
                .assertThat()
                .statusCode(500);
    }
}
