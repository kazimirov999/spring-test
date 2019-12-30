package com.udelphi.integration;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;
class CategoryIntegrationTest extends IntegrationTest {

    @Test
    void shouldPersistNewCategory() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_category.json"))
                .when()
                .post("/categories")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("car"))
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnCategoryById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("car"));
    }

    @Test
    void shouldThrowExceptionWhenGetByWrongId() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }


    @Test
    void shouldReturnListCategories() {

        when()
                .get("/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(4))
                .root("find{it.id == %s}", withArgs(1))
                .body("name", equalTo("car"));
    }

    @Test
    void shouldDeleteCategoryById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/categories/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldUpdateCategoryById() {
        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_category.json"))
                .when()
                .put("/categories/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateByWrongId() {
        //🤷
        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_category.json"))
                .when()
                .put("/categories/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

}
