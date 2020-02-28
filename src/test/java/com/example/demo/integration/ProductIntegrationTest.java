package com.example.demo.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ProductIntegrationTest extends IntegrationTest {

    @Test
    void shouldReturnProductWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_product.json"))
                .when()
                .post("/products")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("name", equalTo("new product"))
                .body("price", equalTo(0));
    }

    @Test
    void shouldReturnProductById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/products/{id}")
                .then()
                .body("name", equalTo("Opel"))
                .body("price", equalTo(5000));
    }

    @Test
    void shouldReturnProductAll() {
        when()
                .get("/products")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all()
                .body("size()", equalTo(3));
    }

    @Test
    void shouldDeleteProductById() {
        given()
                .pathParam("id", "3")
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldUpdateProductById() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_product.json"))
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
