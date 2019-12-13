package com.udelphi.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

class CategoryIntegrationTest extends IntegrationTest {

    @Autowired
    DataSource dataSource;
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
    @Sql(TEST_DATA)
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
    @Sql(TEST_DATA)
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
    @Sql(TEST_DATA)
    void shouldDeleteCategoryById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/categories/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Sql(TEST_DATA)
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
