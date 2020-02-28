package com.example.demo.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;
@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = {
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.datasource.url=jdbc:tc:postgresql:9.6.8:///testdb",
                "spring.jpa.show-sql=true"
        })
@SqlGroup(
        {
                @Sql(scripts = "/test-data.sql", executionPhase = BEFORE_TEST_METHOD),
                @Sql(scripts = "/drop.sql", executionPhase = AFTER_TEST_METHOD)
        }
)
@SqlMergeMode(value = MERGE)

public class IntegrationTest {
    @LocalServerPort
    protected int port;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
