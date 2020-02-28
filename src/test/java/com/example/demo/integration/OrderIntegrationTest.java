package com.example.demo.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderIntegrationTest extends IntegrationTest {


    @Test
    void shouldReturnOrderWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_order.json"))
                .when()
                .post("/orders")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnOrderById() {
        given()
                .pathParam("id", 3)
                .when()
                .get("/orders/{id}")
                .then()
                .log().all()
                .body("id", equalTo(3));
    }

    @Test
    void shouldReturnOrdersAll() {
        when()
                .get("/orders")
                .then()
                .body("size()", equalTo(3));
    }

    @Test
    void shouldDeleteOrderById() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldUpdateOrderById() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_order.json"))
                .when()
                .put("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
