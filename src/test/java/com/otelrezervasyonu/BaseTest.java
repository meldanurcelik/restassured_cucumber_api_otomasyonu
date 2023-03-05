package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected String bookingObject(String firstname, String lastname, int totalAmount) {

        JSONObject body = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalAmount);
        body.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2023-06-22");
        bookingDates.put("checkout", "2023-06-28");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Her şey dahil");

        return body.toString();
    }

    protected String createToken() {

        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString()).log().all()
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();

        return response.jsonPath().getJsonObject("token");
    }

    protected Response createBooking(String firstname, String lastname, int totalAmount) {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingObject(firstname, lastname, totalAmount))
                .post("https://restful-booker.herokuapp.com/booking");

        response
                .then()
                .statusCode(200);

        response.prettyPrint();

        return response;
    }

    protected int createBookingId(String firstname, String lastname, int totalAmount, Boolean depositpaid) {
        return createBooking(firstname, lastname, totalAmount).jsonPath().getJsonObject("bookingid");
    }

}
