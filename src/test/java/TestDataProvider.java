
import org.testng.annotations.DataProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    protected static final String NON_EXISTENT_ID = "ab599abe-4751-4cac-87d0-42037426dade";

@DataProvider(name = "allPostRequests")
public static Object[][] allPostRequests() throws IOException {
    String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/java/testData.json")));
    JSONArray jsonArray = new JSONArray(jsonContent);

    List<TestCaseData> testDataList = new ArrayList<>();

    for (int index = 0; index < jsonArray.length(); index++) {
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        testDataList.add(new TestCaseData(
                jsonObject.getString("requestBody"),
                jsonObject.getInt("statusCode"),
                jsonObject.getString("expectedMessage")

        ));
    }

    return testDataList.stream()
            .map(testCase -> new Object[]{testCase})
            .toArray(Object[][]::new);
}
    @DataProvider(name = "validStatisticRequests")
    public Object[][] validStatisticRequests() {
        return new Object[][] {
                {new TestCaseData("4280c148-d8f4-49af-8f1f-e696bf19998f", 200, "Сохранили объявление - .*")}
        };
    }
    @DataProvider(name = "InvalidStatisticRequests")
    public Object[][] InvalidStatisticRequests() {
        return new Object[][] {
                {" ", 400, "передан некорректный идентификатор объявления"},
                {123456, 400, "передан некорректный идентификатор объявления"},
                {"ab599abe-4751-4cac-87d0-42037426dade", 404, "statistic ab599abe-4751-4cac-87d0-42037426dade not found"},

        };
    }
    @DataProvider(name = "InvalidSellerIdRequests")
    public Object[][] InvalidSellerIdRequests() {
        return new Object[][] {
                {" ", 400, "передан некорректный идентификатор продавца"},
                {"Qweys!279", 400, "передан некорректный идентификатор продавца"},


        };
    }
    @DataProvider(name = "validSellerIdRequests")
    public Object[][] validSellerIdRequests() {
        return new Object[][] {
                {999994, 200}
        };
    }

    @DataProvider(name = "createItemSuccess")
    public Object[][] createItemSuccess() {
        return new Object[][] {
                {SetUpTests.createAd(), 200}
        };
    }

    @DataProvider(name = "InvalidGetIdRequests")
    public Object[][] InvalidGetIdRequests() {
        return new Object[][] {
                {" ", 400, "передан некорректный идентификатор объявления"},
                {123456, 400, "передан некорректный идентификатор объявления"},
                {NON_EXISTENT_ID, 404, "item " + NON_EXISTENT_ID + " not found"}

        };
    }
    @DataProvider(name = "postRequestMaxLenght")
    public Object[][] postRequestMaxLenght() {
        String longName = "А".repeat(101);
        return new Object[][] {
                {String.format("{\"name\": \"%s\", \"price\": 1, \"sellerId\": 999994}", longName), 200, "Сохранили объявление - .*"}
        };
    }
}
