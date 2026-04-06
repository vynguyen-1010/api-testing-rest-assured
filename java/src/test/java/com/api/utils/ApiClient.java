package com.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com"; 
    private String token;

    public ApiClient() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public void setToken(String token) {
        this.token = token;
    }

    private RequestSpecification getBaseSpec() {
        return RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).log().all();
    }

    private RequestSpecification getAuthSpec() {
        return getBaseSpec().cookie("token", token);
    }

    public Response ping() {
        return getBaseSpec().when().get("/ping");
    }

    public Response auth(String username, String password) {
        String body = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        return getBaseSpec().body(body).when().post("/auth");
    }

    public Response getAllBookings() {
        return getBaseSpec().when().get("/booking");
    }

    public Response getBookingById(int bookingId) {
        return getBaseSpec().when().get("/booking/" + bookingId);
    }

    public Response createBooking(Object booking) {
        return getBaseSpec().body(booking).when().post("/booking");
    }

    public Response updateBooking(int bookingId, Object booking) {
        return getAuthSpec().body(booking).when().put("/booking/" + bookingId);
    }

    public Response partialUpdateBooking(int bookingId, Object booking) {
        return getAuthSpec().body(booking).when().patch("/booking/" + bookingId);
    }
    
    public Response deleteBooking(int bookingId) {
        return getAuthSpec().when().delete("/booking/" + bookingId);
    }
}
