package com.udelphi.integration;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

class OrderIntegrationTest extends IntegrationTest {


    @Test
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
    void shouldDeleteOrderById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/orders/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
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
