package com.otelrezervasyonu;

import org.junit.jupiter.api.Test;

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

}