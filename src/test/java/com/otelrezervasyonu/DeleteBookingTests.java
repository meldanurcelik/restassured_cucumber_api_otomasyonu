package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest {

    @Test
    public void deleteBookingTest() {

        String token = createToken();
        int bookingId = createBookingId("Ozan", "Ilhan", 5, true);

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);

        //response.prettyPrint();

    }

}