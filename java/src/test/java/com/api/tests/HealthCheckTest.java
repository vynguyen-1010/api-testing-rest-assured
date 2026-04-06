package com.api.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.testng.annotations.Test;
import com.api.base.BaseTest;
import io.restassured.response.Response;

public class HealthCheckTest extends BaseTest {
    @Test
    public void testPingEndpoint() {
        System.out.println("Testing /ping endpoint");

        Response response = apiClient.ping();

        int statusCode = response.getStatusCode();
        System.out.println("Response status code: " + statusCode);

        response.then().assertThat().statusCode(201);   
        System.out.println("Response body: " + response.getBody().asString());
        assertNotNull(response.getBody());

        System.out.println("Health check passed!");
    }
}
