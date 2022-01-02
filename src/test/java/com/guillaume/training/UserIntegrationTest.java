package com.guillaume.training;

import com.guillaume.training.controller.dto.UserPayload;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/drop_create_schema.sql"})
@ContextConfiguration(initializers = { UserIntegrationTest.Initializer.class})
class UserIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public void shouldAddAUser() {
        String requestBody = objectMapper.writeValueAsString(new UserPayload(1L, "user", "email@email.fr"));

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .assertThat()
                .body(
                        "id", is(1),
                        "name", is("user"),
                        "email", is("email@email.fr")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_user_script.sql"})
    @Test
    public void shouldFindAnExistingUserByID(){
        get("/users/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", is(1),
                        "name", is("user1"),
                        "email", is("email1@email.fr")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_user_script.sql"})
    @Test
    public void shouldFindAllUsers(){
        get("/users")
                .then()
                .assertThat()
                .statusCode(200)
                .body(
                        "id", hasItems(1, 2, 3),
                        "name", hasItems("user1", "user2", "user3"),
                        "email", hasItems("email1@email.fr", "email2@email.fr", "email3@email.fr")
                );
    }

    @Test
    public void shouldReturnAnInternalErrorIfUserDoesNotExists(){
        get("/users/1")
                .then()
                .assertThat()
                .statusCode(404)
                .body(
                        "status", is("NOT_FOUND"),
                        "message", is("Could not find user 1")
                );
    }

    @Sql({"/drop_create_schema.sql", "/create_default_user_script.sql"})
    @Test
    @SneakyThrows
    public void shouldReturnAnInternalErrorIfUserEmailAlreadyExists(){
        String requestBody = objectMapper.writeValueAsString(new UserPayload(4L, "user1", "email1@email.fr"));

        given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(409)
                .body(
                        "status", is("CONFLICT"),
                        "message", is("User already exists for email email1@email.fr")
                );
    }
}