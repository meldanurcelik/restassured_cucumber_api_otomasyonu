package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected String bookingObject() {

        JSONObject body = new JSONObject();
        body.put("firstname", "Melda");
        body.put("lastname", "Celik");
        body.put("totalprice", 222);
        body.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2023-06-22");
        bookingDates.put("checkout", "2023-06-28");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Her şey dahil");

        return body.toString();
    }

    protected Response createBooking() {

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("https://restful-booker.herokuapp.com/booking");

        response.then().log().all().statusCode(200);

        response.prettyPeek();

        return response;
    }

}
