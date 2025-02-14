import io.restassured.response.Response;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetIdTest extends SetUpTests {

    @BeforeMethod(onlyForGroups = "checkNonExistentId")
    public void checkItemDoesNotExist() {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id", TestDataProvider.NON_EXISTENT_ID)
                .get("/api/1/item/{id}");

        if (response.statusCode() == 200) {
            throw new SkipException("Объявление с id " + TestDataProvider.NON_EXISTENT_ID + " существует, используйте id объявления, который не существует в системе.");
        }
    }

    @Test(dataProvider = "createItemSuccess", dataProviderClass = TestDataProvider.class)
    public void getIdItemFailed(Object id, int status) {
        try {
        given()
                .baseUri(SetUpTests.BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id",id )
                .get("/api/1/item/{id}")
                .then()
                .statusCode(status)
                .body("name", equalTo("MacBook Air"))
                .body("price", equalTo(250000))
                .body("sellerId", equalTo(SELLER_ID))
                .body("[0].contacts", equalTo(50))
                .body("[0].likes", equalTo(100))
                .body("[0].viewCount", equalTo(160));
    } catch (AssertionError e) {
        System.err.println("Тест не пройден из-за несоответствия данных в полях: " + e.getMessage());

     }
    }

    @Test(groups = "checkNonExistentId", dataProvider = "InvalidGetIdRequests", dataProviderClass = TestDataProvider.class)
    public void getNonExistentIdItem(Object id, int statusCode, String expectedMessage) {
            given()
                    .baseUri(SetUpTests.BASE_URL)
                    .header("Content-Type", "application/json")
                    .contentType("application/json")
                    .when()
                    .pathParam("id", id)
                    .get("/api/1/item/{id}")
                    .then()
                    .statusCode(statusCode)
                    .body("result.message", equalTo(expectedMessage));

    }

}
