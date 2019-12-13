package com.udelphi.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

class OrderIntegrationTest extends IntegrationTest {


    @Test
    @Sql(TEST_DATA)
    void shouldReturnOrderWithId() {

        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_order.json"))
                .when()
                .post("/orders")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("orderDate", equalTo(973987200000L));
    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnOrderById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/orders/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("orderDate", equalTo(1573423200000L))
                .root("orderItems.find{it.id == %s}", withArgs(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));
    }

    @Test
    void shouldThrowExceptionWhenGetOrderThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnListOrders() {

        when()
                .get("/orders")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .root("find {it.id == %s} ", withArgs(1))
                .body("id", equalTo(1))
                .body("orderDate", equalTo(1573423200000L))
                .appendRoot("orderItems.find{it.id == %s}", withArgs(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnListOrderItemsByOrderId() {

        given()
                .pathParam("id", "1")
                .when()
                .get("/orders/{id}/orderItems")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1))
                .root("find {it.id == %s} ", withArgs(1))
                .body("id", equalTo(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldDeleteOrderById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Sql(TEST_DATA)
    void shouldUpdateOrderById() {
        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_order.json"))
                .when()
                .put("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateOrderThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_order.json"))
                .when()
                .put("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

}
