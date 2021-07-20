package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest.RequestHelpers;
import rest.RequestMethods;
import utils.Log;
import api.BookmakerApiCalls;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuickCardApiCalls {
    public final String RESOURCES_PATH = "src/test/resources/data/bookmaker/quickcard/";
    public final String INSERT_QUICK_CARD_INFO = RESOURCES_PATH + "insert-quick-card-info.json";
    public final String UPDATE_QUICK_CARD_INFO = RESOURCES_PATH + "update-quick-card-info.json";
    public final String CREATE_QUICK_CARD_IMAGE = RESOURCES_PATH + "upload-quick-card-logo.json";
    public final String QUICK_CARD_IMAGE = "src/test/resources/Quickcard.jpg";
    public String filePath;
    final Map<String, String> requestHeaders = new HashMap<>();
    final RequestMethods requestMethods = new RequestMethods();
    final RequestHelpers requestHelpers = new RequestHelpers();
    final AppConfig envConfig = ConfigFactory.create(AppConfig.class);

    public QuickCardApiCalls() {
          requestHeaders.put("Authorization", Authentication.token);
    }

    public String addQuickCardInfo(String quickCardName, int quickCardRating, String bookmakerId) throws Exception {
       return requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                updateQuickCardInfoBody(INSERT_QUICK_CARD_INFO,quickCardName, quickCardRating), requestHeaders, 200).getBody().asString();
    }

    public String addDuplicateQuickCardInfo(String quickCardName, int quickCardRating, String bookmakerId) throws Exception {
        return requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                updateQuickCardInfoBody(INSERT_QUICK_CARD_INFO,quickCardName, quickCardRating), requestHeaders, 200).getBody().asString();
    }

    public JSONObject getRequestBody(String path) throws IOException, ParseException {
        FileReader reader = new FileReader(path);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        return jsonObject;
    }

    public JSONObject updateQuickCardInfoBody(String path, String quickCardName, int quickCardRating) throws IOException, ParseException {
        BookmakerApiCalls bookmakerApiCalls =new BookmakerApiCalls();
        JSONObject jsonObject =bookmakerApiCalls.getRequestBodyUsingTagNamesInsteadOfTagIdsFromJsonFile(path);
        JSONObject images = (JSONObject) jsonObject.get("quickCard");
        images.put("message", quickCardName);
        images.put("rank", quickCardRating);
        return jsonObject;
    }

    public void updateQuickCardInfo(String bookmakerId) throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId,
                UPDATE_QUICK_CARD_INFO, requestHeaders, 200);
    }

    public JSONObject getQuickCardImageFilePathBody(String path, String filePath) throws IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        JSONObject images = (JSONObject) jsonObject.get("images");
        images.put("quickCardLogo", filePath);
        return jsonObject;
    }

    public void uploadBookmakerQuickCardImage(String bookmakerId) throws Exception {
        File file = new File(QUICK_CARD_IMAGE);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id",bookmakerId).multiPart("type", "bookmakers")
                .header("Authorization", Authentication.token);
        Log.warn("Bearer Token" + Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "/images/file");
        Log.warn("Crop Call" + response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, 200);
        filePath = JsonPath.read(response.asString(), "filePath");
        RequestSpecification requestPut = RestAssured.given().contentType(ContentType.JSON).header("Authorization",
                Authentication.token);
        Log.warn(String.valueOf(bookmakerId));
        Response response1 = requestPut.when().body(getQuickCardImageFilePathBody(CREATE_QUICK_CARD_IMAGE, filePath))
                .put(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId);
        Log.warn(response1.getBody().asString());
    }

    public void addFiveQuickCards() throws Exception {
        addQuickCardInfo("Quick_Card_1", 1, BookmakerApiCalls.bookmaker_Id_1);
        addQuickCardInfo("Quick_Card_2", 2, BookmakerApiCalls.bookmaker_Id_2);
        addQuickCardInfo("Quick_Card_3", 3, BookmakerApiCalls.bookmaker_Id_3);
        addQuickCardInfo("Quick_Card_4", 4, BookmakerApiCalls.bookmaker_Id_4);
        addQuickCardInfo("Quick_Card_5", 5, BookmakerApiCalls.bookmaker_Id_5);
    }
}
