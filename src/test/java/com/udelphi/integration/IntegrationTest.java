package com.udelphi.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = {
                "spring.datasource.platform=postgresql",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.datasource.url=jdbc:tc:postgresql:9.6.8:///testdb"
        })
@Sql(value = "/drop.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SqlMergeMode(value = SqlMergeMode.MergeMode.MERGE)
public class IntegrationTest {

    @LocalServerPort
    protected int port;

    protected static final String TEST_DATA = "/test-data.sql";


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
