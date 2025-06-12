package com.elza.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingChangePUT extends TestBase {


    @Test(dependsOnMethods = "com.elza.api.BookingTestsPOST.createBooking")
    public void updateBooking() {
        int bookingId = BookingTestsPOST.bookingId;

        if (bookingId == 0) {
            throw new RuntimeException("bookingId not set. Please run the POST test first.");
        }

        // New booking data
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2024-07-01");
        dates.put("checkout", "2024-07-10");

        Map<String, Object> updatedBooking = new HashMap<>();
        updatedBooking.put("firstname", "ELZA_PUT");
        updatedBooking.put("lastname", "UPDATED");
        updatedBooking.put("totalprice", 200);
        updatedBooking.put("depositpaid", false);
        updatedBooking.put("bookingdates", dates);
        updatedBooking.put("additionalneeds", "Dinner");

        String token = AuthTests.getToken();

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBooking)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("PUT Update Response:\n" + response.asPrettyString());
    }
}