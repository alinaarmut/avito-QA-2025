import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetStatisticsTest {

    @Test(dataProvider = "validStatisticRequests",dataProviderClass = TestDataProvider.class)
    public void getIdItemSuccess(TestCaseData  testCaseData) {
        given()
                .baseUri(SetUpTests.BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id",testCaseData.requestBody() )
                .get("/api/1/statistic/{id}")
                .then()
                .statusCode(testCaseData.statusCode())
                .body("[0].contacts", equalTo(54))
                .body("[0].likes", equalTo(0))
                .body("[0].viewCount", equalTo(32));
    }


    @Test(dataProvider = "InvalidStatisticRequests",dataProviderClass = TestDataProvider.class)
    public void getIdItemFailed(Object id, int statusCode, String expectedMessage) {
        given()
                .baseUri(SetUpTests.BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .when()
                .pathParam("id", id )
                .get("/api/1/statistic/{id}")
                .then()
                .statusCode(statusCode)
                .body("result.message", equalTo(expectedMessage));
    }
}
