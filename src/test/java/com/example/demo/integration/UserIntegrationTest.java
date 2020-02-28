package com.example.demo.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class UserIntegrationTest extends IntegrationTest {

    @Test
    void shouldReturnUserWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_user.json"))
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("new name"))
                .body("email", equalTo("new mail"));
    }

    @Test
    void shouldReturnUserById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("email", equalTo("max@gmail.com"));
    }

    @Test
    void shouldReturnUsersAll() {
        when()
                .get("/users")
                .then()
                .body("size()", equalTo(3));
    }

    @Test
    void shouldDeleteUserById() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/users/{id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldUpdateUserById() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_user.json"))
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
