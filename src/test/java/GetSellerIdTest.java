import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetSellerIdTest {

    @Test(dataProvider = "validSellerIdRequests", dataProviderClass = TestDataProvider.class )
    public void getSellerIdSuccess(int id, int statusCode) {
        Response response = given()
                .baseUri(SetUpTests.BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id",id )
                .get("/api/1/{id}/item");

        response
                .then()
                .statusCode(statusCode)
                .body("$", hasSize(greaterThan(0)));
    }
    @Test(dataProvider = "InvalidSellerIdRequests", dataProviderClass = TestDataProvider.class)
    public void getSellerInvalidId(Object id, int statusCode, String expectedMessage) {
        Response response = given()
                .baseUri(SetUpTests.BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id", id)
                .get("/api/1/{id}/item");

        response
                .then()
                .statusCode(statusCode)
                .body("result.message", equalTo(expectedMessage));
    }
}
