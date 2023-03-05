package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CreateBookingTests extends BaseTest {

    @Test
    public void createBookingTest() {

        Response response = createBooking("Melda", "Celik", 222);

        Assertions.assertEquals("Melda", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Celik", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(222, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));

    }

}