package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest {

    @Test
    public void partiallyUpdateBookingTest() {

        String token = createToken();
        int bookingId = createBookingId("Ozan", "Ilhan", 10, false);

        JSONObject body = new JSONObject();
        body.put("firstname", "Ayse");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(body.toString())
                .patch("/booking/" + bookingId);

        //response.prettyPrint();

        Assertions.assertEquals("Ayse", response.jsonPath().getJsonObject("firstname"));

    }

}