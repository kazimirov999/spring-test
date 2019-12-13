package com.udelphi.integration;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

class OrderItemIntegrationTest extends IntegrationTest {

    @Test
    @Sql(TEST_DATA)
    void shouldReturnOrderItemWithId() {


        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_order_item.json"))
                .when()
                .post("/orderItems")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(2));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnOrderItemById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/orderItems/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));
    }

    @Test
    void shouldThrowExceptionWhenGetOrderItemThenIdNotFound() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/orderItems/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1"));
    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnListCategories() {

        when()
                .get("/orderItems")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .root("find{it.id = %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldDeleteOrderItemById() {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/orderItems/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateOrderItemThenIdNotFound() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_order_item.json"))
                .when()
                .put("/orderItems/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1"));
    }

    @Test
    @Sql(TEST_DATA)
    void shouldUpdateOrderItemById() {


        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_order_item.json"))
                .when()
                .put("/orderItems/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
