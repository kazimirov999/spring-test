package com.udelphi.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

class CommentIntegrationTest extends IntegrationTest {

    @Test
    @Sql(TEST_DATA)
    void shouldReturnPersistComment() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_comment.json"))
                .when()
                .post("/comments")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("text", equalTo("new comment"))
                .body("userId", equalTo(1))
                .body("productId", equalTo(1));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnCommentById() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("text", equalTo("First comment"))
                .body("userId", equalTo(1))
                .body("productId", equalTo(1))
                .root("comments.find{it.id == %s}", withArgs(2))
                .body("text", equalTo("Sub comment"));
    }

    @Test
    void shouldThrowExceptionWhenGetCommentByWrongId() {
        given()
                .pathParam("id", "1000")
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnListSubCommentsByParentCommentId() {
        given()
                .pathParam("id", "1")
                .when()
                .get("/comments/{id}/comments")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(1))
                .root("find{it.id == %s}", withArgs(2))
                .body("text", equalTo("Sub comment"))
                .body("userId", equalTo(2));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldReturnListComments() {

        when()
                .get("/comments")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(3))
                .root("find{it.id == %s}", withArgs(1))
                .body("id", equalTo(1))
                .body("text", equalTo("First comment"))
                .body("userId", equalTo(1))
                .body("productId", equalTo(1))
                .appendRoot("comments.find{it.id = %s}", withArgs(2))
                .body("text", equalTo("Sub comment"));

    }

    @Test
    @Sql(TEST_DATA)
    void shouldDeleteCommentById() {
        given()
                .pathParam("id", "1")
                .when()
                .delete("/comments/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


    @Test
    @Sql(TEST_DATA)
    void shouldUpdateCommentById() {
        given()
                .pathParam("id", "1")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_comment.json"))
                .when()
                .put("/comments/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldThrowExceptionWhenUpdateCommentThenIdNotFound() {
        given()
                .pathParam("id", "1000")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_category.json"))
                .when()
                .put("/comments/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Entity not found with id: 1000"));

    }
}
