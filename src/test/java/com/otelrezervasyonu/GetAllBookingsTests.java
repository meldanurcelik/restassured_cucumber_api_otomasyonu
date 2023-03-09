package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest {

    @Test
    public void getAllBookingTest() {

        given(spec)
                .when()
                .get("/booking")
                .then()
                //.log().all()
                .statusCode(200);

    }

    @Test
    public void getBookings_with_firstname_filter_test() {

        int bookingId = createBookingId("Melda", "Celik", 10, false);

        spec.queryParam("firstname", "Melda");

        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> list = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(list.contains(bookingId));

    }

}