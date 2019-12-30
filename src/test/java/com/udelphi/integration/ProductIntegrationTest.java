package com.udelphi.integration;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

class ProductIntegrationTest extends IntegrationTest {

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
                .body("description", equalTo("description"))
                .body("price", equalTo(2000.4f))
                .body("categories.size()", is(2))
                .root("categories.find{it.name == '%s'}", withArgs("category_1"))
                .body("id", notNullValue());
    }

    @Test
    void shouldReturnProductById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/products/{id}")
                .then()
                .body("name", equalTo("Tesla"))
                .body("description", equalTo("Tesla Model S"))
                .body("price", equalTo(40000f))
                .body("categories.size()", is(2))
                .root("categories.find{it.id == %s}")
                .body("name", withArgs(1), equalTo("car"))
                .noRoot()
                .body("orderItems.size()", equalTo(1))
                .root("orderItems.find{it.id == %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("quantity", equalTo(1))
                .noRoot()
                .body("comments.size()", equalTo(1))
                .root("comments.find{it.id == %s}", withArgs(1))
                .body("text", equalTo("First comment"))
                .body("userId", equalTo(1));
    }

    @Test
    void shouldThrowExceptionWhenGetProductThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

    @Test
    void shouldReturnListProductCommentsByProductId() {
        given()
                .pathParam("productId", "1")
                .when()
                .get("/products/{productId}/comments")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(1))
                .root("find{it.id == %s}", withArgs(1))
                .body("text", equalTo("First comment"))
                .body("productId", equalTo(1));
    }

    @Test
    void shouldReturnListProductOrderItemsByProductId() {
        given()
                .pathParam("productId", "1")
                .when()
                .get("/products/{productId}/orderItems")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(1))
                .root("find{it.id == %s}", withArgs(1))
                .body("orderId", equalTo(1))
                .body("productId", equalTo(1))
                .body("quantity", equalTo(1));
    }

    @Test
    void shouldReturnListProductCategoriesItemsByProductId() {
        given()
                .pathParam("productId", "1")
                .when()
                .get("/products/{productId}/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(2))
                .root("find{it.id == %s}", withArgs(1))
                .body("name", equalTo("car"));
    }

    @Test
    void shouldReturnListProducts() {

        when()
                .get("/products")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(3))
                .root("find{it.id == %s}", withArgs(1))
                .body("name", equalTo("Tesla"))
                .body("description", equalTo("Tesla Model S"))
                .body("price", equalTo(40000f))
                .body("categories.size()", is(2))
                .appendRoot("categories.find{it.id == %s}")
                .body("name", withArgs(1), equalTo("car"))
                .root("find{it.id == %s}", withArgs(1))
                .body("orderItems.size()", equalTo(1))
                .appendRoot("orderItems.find{it.id == %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("quantity", equalTo(1))
                .root("find{it.id == %s}", withArgs(1))
                .body("comments.size()", equalTo(1))
                .appendRoot("comments.find{it.id == %s}", withArgs(1))
                .body("text", equalTo("First comment"))
                .body("userId", equalTo(1));
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
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_product.json"))
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateProductThenIdNotFound() {

        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_product.json"))
                .when()
                .put("/products/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));
    }

}
