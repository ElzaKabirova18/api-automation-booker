package com.elza.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookingGET extends TestBase {

    @Test(dependsOnMethods = "com.elza.api.BookingTestsPOST.createBooking")
    public void getBookingById() {
        int bookingId = BookingTestsPOST.bookingId;

        if (bookingId == 0) {
            throw new RuntimeException("bookingId not set. Please run the POST test first.");
        }

        System.out.println("Fetching booking with ID: " + bookingId);

                 given()
                .accept(ContentType.JSON)
                .log().all()  // Log request details for debugging
                .when()
                .get("/booking/" + bookingId)
                .then()
                .log().all()  // Log response details for debugging
                .statusCode(200);

        System.out.println("Booking Details:\n");
    }
}