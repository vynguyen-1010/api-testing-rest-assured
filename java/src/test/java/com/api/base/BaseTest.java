package com.api.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.api.utils.ApiClient;

import io.restassured.response.Response;

public class BaseTest {
    protected ApiClient apiClient;
    protected int currentBookingId;

    @BeforeClass
    public void setup() {
        System.out.println("=== Setting up test class ===");

        apiClient = new ApiClient();

        Response authResponse = apiClient.auth("admin", "password123");
        String token = authResponse.jsonPath().getString("token");

        apiClient.setToken(token);

        System.out.println("Token obtained: " + token);
        System.out.println("=== Setup complete ===\n");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(">>> Starting test: " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(">>> Finished test");

        if(currentBookingId != 0) {
            try {
                apiClient.deleteBooking(currentBookingId);
                System.out.println("Cleaned up booking ID: " + currentBookingId);
            } catch (Exception e) {
                System.out.println("Failed to cleanup booking: " + currentBookingId);
            }
            currentBookingId = 0;
        }

        System.out.println();
    }
}
