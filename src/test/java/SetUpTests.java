import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SetUpTests {
    public static final String BASE_URL = "https://qa-internship.avito.com";
    protected static final int SELLER_ID = 999994;

    public static String createAd() {
        String requestBody = "{\n" +
                "  \"name\": \"MacBook Air\",\n" +
                "  \"price\": 250000,\n" +
                "  \"sellerId\": " + SELLER_ID + ",\n" +
                "  \"statistics\": {\n" +
                "    \"contacts\": 50,\n" +
                "    \"likes\": 100,\n" +
                "    \"viewCount\": 160\n" +
                "  }\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(requestBody)
                .post("/api/1/item")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String status = response.jsonPath().getString("status");
        if (status != null && status.contains(" - ")) {
            return status.split(" - ")[1];
        } else {
            throw new IllegalStateException("Не удалось извлечь ID из ответа: " + status);
        }
    }
}
