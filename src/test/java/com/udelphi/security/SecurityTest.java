package com.udelphi.security;

import com.udelphi.integration.IntegrationTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

public class SecurityTest extends IntegrationTest {

    @Test
    public void test(){
        given()
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/request/new_role.json"))
                .when()
                .post("/roles")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deleteUserRole(){
        given()
                .pathParam("id", "1")
                .pathParam("roleId", "1")
                .when()
                .delete("/users/{id}/roles/{roleId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
    @Test
    public void deleteAnotherUserRole(){
        given()
                .auth()
                .basic("vasya@gmail.com", "admin")
                .pathParam("id", "1")
                .pathParam("roleId", "1")
                .when()
                .delete("/users/{id}/roles/{roleId}")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}
