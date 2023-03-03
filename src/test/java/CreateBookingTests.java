
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests {

    //curl -X POST \
    //https://restful-booker.herokuapp.com/booking \
    //        -H 'Content-Type: application/json' \
    //        -d '{
    //        "firstname" : "Jim",
    //        "lastname" : "Brown",
    //        "totalprice" : 111,
    //        "depositpaid" : true,
    //        "bookingdates" : {
    //            "checkin" : "2018-01-01",
    //            "checkout" : "2019-01-01"
    //},
    //        "additionalneeds" : "Breakfast"
    //}'

    @Test
    public void createBookingTest() {

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

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        response.then().log().all().statusCode(200);

        response.prettyPeek();

        Assertions.assertEquals("Melda", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Celik", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(222, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));

    }

}