package com.udelphi.integration;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;

@SpringBootTest(webEnvironment = RANDOM_PORT,
        properties = {
                "spring.datasource.platform=postgresql",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.datasource.url=jdbc:tc:postgresql:9.6.8:///testdb"
        })
@SqlGroup({
        @Sql(value = "/test-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "/drop.sql", executionPhase = AFTER_TEST_METHOD)
})
@SqlMergeMode(value = SqlMergeMode.MergeMode.MERGE)
public class IntegrationTest {

    @LocalServerPort
    protected int port;

    protected static final String TEST_DATA = "/test-data.sql";


    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("john@gmail.com");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }
}
