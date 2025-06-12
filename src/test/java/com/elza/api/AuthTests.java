package com.elza.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthTests{

    public static String getToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("https://restful-booker.herokuapp.com/auth");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to get token: " + response.asString());
        }

        String token = response.jsonPath().getString("token");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not found in response: " + response.asString());
        }

        return token;
    }

    public static void main(String[] args) {
        String token = getToken();
        System.out.println("Token: " + token);
    }
}