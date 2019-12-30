package com.udelphi.integration;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

class RoleIntegrationTest extends IntegrationTest {


    @Test
    void shouldReturnRoleWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_role.json"))
                .when()
                .post("/roles")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("user"))
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnRoleById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/roles/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("user"));
    }

    @Test
    void shouldThrowExceptionWhenGetRoleThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldReturnListRoles() {

        when()
                .get("/roles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(3))
                .root("find{it.id == %s}", withArgs(1))
                .body("name", equalTo("user"));

    }

    @Test
    void shouldDeleteRoleById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    void shouldUpdateRoleById() {
        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_role.json"))
                .when()
                .put("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateRoleThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_role.json"))
                .when()
                .put("/roles/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

}
