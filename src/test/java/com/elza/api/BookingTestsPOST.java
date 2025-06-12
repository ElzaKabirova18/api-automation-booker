package com.elza.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingTestsPOST extends TestBase{

    public static int bookingId;

    @Test
    public void createBooking() {
        Map<String, String> dates = new HashMap<>();
        dates.put("checkin", "2024-06-10");
        dates.put("checkout", "2024-06-15");

        Map<String, Object> booking = new HashMap<>();
        booking.put("firstname", "ELZA787");
        booking.put("lastname", "HOROSHO");
        booking.put("totalprice", 123);
        booking.put("depositpaid", true);
        booking.put("bookingdates", dates);
        booking.put("additionalneeds", "Lunch");


               Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "  \"firstname\": \"ELZA777\",\n" +
                        "  \"lastname\": \"HOROSHO\",\n" +
                        "  \"totalprice\": 123,\n" +
                        "  \"depositpaid\": true,\n" +
                        "  \"bookingdates\": {\n" +
                        "    \"checkin\": \"2024-06-10\",\n" +
                        "    \"checkout\": \"2024-06-15\"\n" +
                        "  },\n" +
                        "  \"additionalneeds\": \"Lunch\"\n" +
                        "}")
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().response();

        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("BookingID : " + bookingId);
    }

}
