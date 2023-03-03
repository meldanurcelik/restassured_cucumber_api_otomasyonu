import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests {

    @Test
    public void getBookingByIdTest() {

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/415");
        //.then().log().all()
        //.statusCode(200);

        response.then().log().all().statusCode(200);

        response.prettyPeek();

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Bob", firstname);
        Assertions.assertEquals("Smith", lastname);
        Assertions.assertEquals(111, totalprice);

    }

}