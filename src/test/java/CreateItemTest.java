import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

public class CreateItemTest extends SetUpTests{

    @Test
    public void createAdSuccess() {
        String adId = createAd();
        assert adId != null && !adId.isEmpty() : "ID объявления пустой";
    }

    @Test
    public void testUniqueAdIdForSameInput() {
        String adId1 = createAd();
        String adId2 = createAd();

        assert adId1 != null && !adId1.isEmpty() : "Первый ID объявления пустой";
        assert adId2 != null && !adId2.isEmpty() : "Второй ID объявления пустой";
        assert !adId1.equals(adId2) : "ID объявлений совпадают, но должны быть уникальными";
    }

    @Test(dataProvider = "allPostRequests", dataProviderClass = TestDataProvider.class)
    public void testCreateAdWithInvalidData(TestCaseData testCaseData) {
        Response response = given()
                .baseUri(BASE_URL)
                .body(testCaseData.requestBody())
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .post("/api/1/item");

        response.then()
                .statusCode(testCaseData.statusCode())
                .body("status", matchesPattern(testCaseData.expectedMessage()));
     }

    @Test(dataProvider = "postRequestMaxLenght", dataProviderClass = TestDataProvider.class)
    public void testCreateAdWithMaxNameLength(String requestBody, int status, String expectedMessage) {
        Response response = given()
                .baseUri(SetUpTests.BASE_URL)
                .body(requestBody)
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .post("/api/1/item");
        response.then()
                .statusCode(status)
                .body("status", matchesPattern(expectedMessage));
    }
}


