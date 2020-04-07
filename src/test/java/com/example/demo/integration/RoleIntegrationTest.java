package com.example.demo.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class RoleIntegrationTest extends IntegrationTest {

    @Test
    void shouldReturnRoleWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_role.json"))
                .when()
                .post("/roles")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("new role"));
    }

    @Test
    void shouldReturnRoleById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/roles/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("name", equalTo("admin"));
    }

    @Test
    void shouldReturnRolesAll() {
        when()
                .get("/roles")
                .then()
                .body("size()", equalTo(3));
    }

    @Test
    void shouldDeleteRoleById() throws InterruptedException {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/roles/{id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    void shouldUpdateRoleById() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_role.json"))
                .when()
                .put("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
