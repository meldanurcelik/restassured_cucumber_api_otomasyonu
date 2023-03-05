package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest {

    @Test
    public void getBookingByIdTest() {

        Response newBooking = createBooking("Melda", "Celik", 222);
        int reservationId = newBooking.jsonPath().getJsonObject("bookingid");

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + reservationId);
        //.then().log().all()
        //.statusCode(200);

        response.then().log().all().statusCode(200);

        response.prettyPeek();

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Melda", firstname);
        Assertions.assertEquals("Celik", lastname);
        Assertions.assertEquals(222, totalprice);

    }

}