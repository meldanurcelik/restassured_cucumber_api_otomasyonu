package com.otelrezervasyonu;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    @BeforeEach
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    protected int createBookingId(String firstname, String lastname, int totalAmount, Boolean depositpaid) {
        return createBooking(firstname, lastname, totalAmount).jsonPath().getJsonObject("bookingid");
    }

    protected Response createBooking(String firstname, String lastname, int totalAmount) {

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(bookingObject(firstname, lastname, totalAmount))
                .post("/booking");

        response
                .then()
                .statusCode(200);

        response.prettyPrint();

        return response;
    }

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

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString()).log().all()
                .post("/auth");

        response.prettyPrint();

        return response.jsonPath().getJsonObject("token");
    }

}
