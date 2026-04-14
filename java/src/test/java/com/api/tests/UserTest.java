package com.api.tests;

import com.api.base.BaseTest;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class UserTest extends BaseTest {
    private int createdUserId;

    @Test
    public void testGetAllUsers() {
        Response response = getUsers();

        response.then().statusCode(200)
            .body("users", notNullValue())
            .body("users.size()", greaterThan(0));
    }
}
