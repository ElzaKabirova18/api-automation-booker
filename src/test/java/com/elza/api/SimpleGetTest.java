package com.elza.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;



    public class SimpleGetTest {
        public static void main(String[] args) {
            RestAssured.baseURI = "https://restful-booker.herokuapp.com";

            int bookingId = 698;  // Use a real bookingId from your POST test

            Response response = RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .when()
                    .get("/booking/" + bookingId)
                    .then()
                    .statusCode(200)
                    .extract().response();

            System.out.println(response.asPrettyString());
        }
    }

