package com.example.demo.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CommentIntegrationTest extends IntegrationTest {

    @Test
    void shouldReturnCommentWithId() {
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_comment.json"))
                .when()
                .post("/comments")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("text", equalTo("new comment"));
    }

    @Test
    void shouldReturnCommentById() {
        given()
                .pathParam("id", 1)
                .when()
                .get("/comments/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("text", equalTo("best price"))
                .log().all();
    }

    @Test
    void shouldReturnCommentsAll() {
        when()
                .get("/comments")
                .then()
                .body("size()", equalTo(3));
    }

    @Test
    void shouldDeleteCommentById() throws InterruptedException {
        given()
                .pathParam("id", 1)
                .when()
                .delete("/comments/{id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    void shouldUpdateCommentById() {
        given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/update_comment.json"))
                .when()
                .put("/comments/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
