package com.udelphi.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

class UserIntegrationTest extends IntegrationTest {

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
                .body("name", equalTo("John"))
                .body("email", equalTo("john@gmail.com"))
                .root("roles.find{it.name == '%s'}", withArgs("admin"))
                .body("id", notNullValue());
    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnUserById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/users/{id}")
                .then()
                .body("id", equalTo(1))
                .body("name", equalTo("John"))
                .body("email", equalTo("john@gmail.com"))
                .root("roles.find{it.id == %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("name", equalTo("client"))
                .root("comments.find {it.id == %s}", withArgs(1))
                .body("text", equalTo("First comment"))
                .root("orders.find {it.id == %s}", withArgs(1))
                .body("orderDate", equalTo(1573423200000L))
                .body("clientId", equalTo(1));
    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnAllProductFromUser() {
        given()
                .pathParam("userId", "1")
                .when()
                .get("/users/{userId}/products")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1))
                .root("find{it.id == %s} ", withArgs(1))
                .body("name", equalTo("Tesla"));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnAllRolesFromUser() {
        given()
                .pathParam("userId", "1")
                .when()
                .get("/users/{userId}/roles")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .root("find{it.id == %s} ", withArgs(1))
                .body("name", equalTo("client"));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnAllOrdersFromUser() {
        given()
                .pathParam("userId", "1")
                .when()
                .get("/users/{userId}/orders")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1))
                .root("find{it.id == %s} ", withArgs(1))
                .body("orderDate", equalTo(1573423200000L));

    }

    @Test
    void shouldThrowExceptionWhenGetUserThenIdNotFound() {

        given()
                .pathParam("id", "1")
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1"));
    }


    @Test
    @Sql(TEST_DATA)
    void shouldReturnListUsers() {

        when()
                .get("/users")
                .then()
                .body("size()", equalTo(2))
                .root("find{it.id == %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("name", equalTo("John"))
                .body("email", equalTo("john@gmail.com"))
                .appendRoot("roles.find{it.id == %s}", withArgs(2))
                .body("id", equalTo(2))
                .body("name", equalTo("admin"))
                .root("find{it.id == %s}", withArgs(1))
                .appendRoot("comments.find {it.id == %s}", withArgs(1))
                .body("text", equalTo("First comment"));
    }

    @Test
    @Sql(TEST_DATA)
    void shouldDeleteUserById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    @Sql(TEST_DATA)
    void shouldUpdateUserById() {

        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_user.json"))
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserThenIdNotFound() {

        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_user.json"))
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

}
