package com.api.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    protected static final String BASE_URL = "https://dummyjson.com";
    protected RequestSpecification authRequestSpec;
    protected String accessToken;
    protected long tokenExpiryTime;
    protected int createdUserId;

    private static final long TOKEN_LIFETIME_MS = 10*60*1000; //assume token will be expired in 10 mins

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        refreshToken();
    }
    
    @BeforeMethod
    public void ensureValidToken() {
        if (authRequestSpec == null || System.currentTimeMillis() > tokenExpiryTime) {
            System.out.println("Token null or expired! Getting new token...");
            refreshToken();
        }
    }

    private void refreshToken() {
        String loginBody = "{ \"username\": \"emilys\", \"password\": \"emilyspass\" }";
        
        Response loginResponse = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(loginBody)
            .when()
            .post("/auth/login");
        
        if (loginResponse.getStatusCode() == 200) {
            accessToken = loginResponse.jsonPath().getString("accessToken");
            authRequestSpec = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", accessToken)
                .header("Cookie", "accessToken=" + accessToken);
            
            tokenExpiryTime = System.currentTimeMillis() + TOKEN_LIFETIME_MS;
            System.out.println("New token obtained, expires in 10 minutes");
        } else {
            throw new RuntimeException("Cannot proceed without authentication");
        }
    }

    protected Response getUsers(){
        return authRequestSpec.when().get("/users");
    }
    
    protected Response getUserById(int userId) {
        return authRequestSpec.when().get("/users/" + userId);
    }

    protected Response createUser(Object user) {
        return authRequestSpec.when().post("/users/add");
    }

    protected Response updateUser(int userId, Object user) {
        return authRequestSpec.body(user).when().put("/users/" + userId);
    } 

    protected Response patchUser(int userId, Object user) {
        return authRequestSpec.body(user).when().patch("/users/" + userId);
    }

    protected Response deleteUser(int userId) {
        return authRequestSpec.when().delete("/users/" + userId);
    }
}
