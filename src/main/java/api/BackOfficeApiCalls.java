package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rest.RequestHelpers;
import rest.RequestMethods;
import utils.Log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackOfficeApiCalls {

    public final String BACKOFFICE_PATH = "src/test/resources/data/backoffice/";
    public final String CREATE_TAG_GROUP = BACKOFFICE_PATH + "tags/create-tag-group.json";
    public final String UPDATE_CREATED_TAG_GROUP = BACKOFFICE_PATH + "tags/update-created-tag-group.json";
    public final String COUNTRIES_TAG_GROUP_CODE_ZMB_AGE_18 = BACKOFFICE_PATH + "tags/countries-codezmb-age18.json";
    public final String COUNTRIES_TAG_GROUP_CODE_ZMBIK_AGE_21 = BACKOFFICE_PATH + "tags/countries-codezmbik-age21.json";
    private Response createTagGroupResponse;
    private Response updateTagGroupResponse;
    final RequestMethods requestMethods = new RequestMethods();
    final RequestHelpers requestHelpers = new RequestHelpers();
    final AppConfig envConfig = ConfigFactory.create(AppConfig.class);
    public static final String tagGroupName = "selenium-tag-group-test" + System.currentTimeMillis();
    public static final String tagName = "selenium-tag-name-test" + System.currentTimeMillis();
    public static String tagGroupId;
    final Map<String, String> requestHeaders = new HashMap<>();

    public BackOfficeApiCalls() {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public void createTagGroup() throws Exception {
        Log.warn(envConfig.getApiReviewsService() + "/tag-groups");
        Log.warn(String.valueOf(requestHeaders));
        this.createTagGroupResponse = requestMethods.makePostRequest(envConfig.getApiReviewsService() + "/tag-groups",
                setTagGroupName(CREATE_TAG_GROUP, tagGroupName, tagName), requestHeaders, 200);
        tagGroupId = (JsonPath.read(createTagGroupResponse.asString(), "$.document.id"));
    }

    public JSONObject setTagGroupName(String path, String tagGroupName, String tagName) throws IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        jsonObject.replace("name", tagGroupName);
        JSONArray tagsArray = (JSONArray) jsonObject.get("tags");
        JSONObject itemArr = (JSONObject) tagsArray.get(0);
        itemArr.replace("name", tagName);
        return jsonObject;
    }

    public void updateTagGroup() throws Exception {
        updateTagGroupResponse = requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/tag-groups/" + tagGroupId,
                setTagGroupName(UPDATE_CREATED_TAG_GROUP, tagGroupName + "_edit", tagName + "_edit"), requestHeaders, 200);
        System.out.println(updateTagGroupResponse);
    }

    public void setCountryCodeAndAge() throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/tag-groups/" + "5ece82f0053031001047ca5b",
                requestHelpers.getRequestBody(COUNTRIES_TAG_GROUP_CODE_ZMB_AGE_18), requestHeaders, 200);
    }

    public void unsetCountryCodeAndAge() throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/tag-groups/" + "5ece82f0053031001047ca5b",
                requestHelpers.getRequestBody(COUNTRIES_TAG_GROUP_CODE_ZMBIK_AGE_21), requestHeaders, 200);
    }

    public int getStatusCodeWhenAddSameTagGroup() throws Exception {
        Response response = requestMethods.makePostRequest(envConfig.getApiReviewsService() + "/tag-groups",
                setTagGroupName(CREATE_TAG_GROUP, tagGroupName, tagName), requestHeaders, 409);
        return response.getStatusCode();
    }

    public String getTagGroupById() {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups/" + tagGroupId, requestHeaders, 200);
        return tagGroupId = (JsonPath.read(response.asString(), "$.id"));
    }

    public ArrayList getUpdatedTagGroupValues() {
        ArrayList<Object> values = new ArrayList<>();
        System.out.println(updateTagGroupResponse.getBody().asString());
        String tagGroupName = (JsonPath.read(updateTagGroupResponse.asString(), "$.document.slug"));
        String tagName = (JsonPath.read(updateTagGroupResponse.asString(), "$.document.tags[0].name"));
        values.add(tagGroupName);
        values.add(tagName);
        return values;
    }

    public void deleteTagGroupById(String tagGroupId) {
        requestMethods.makeDeleteRequest(envConfig.getApiReviewsService() + "/tag-groups/" + tagGroupId, requestHeaders, 204);
    }

    public String getProductIdByProductName(String productName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiContentServiceUrl() + "/products?title=Winners.net", requestHeaders, 200);
        return JsonPath.read(response.asString(), "$..id").toString().split(",")[0].split("\\[")[1];
    }

    public String getPageIdByNameFromApi(String pageName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiContentServiceUrl() + "/pages?productId="+getProductIdByProductName("Winners.net")+"&title="+pageName, requestHeaders, 200);
        return JsonPath.read(response.asString(), "$.pageList..id").toString().split("\"")[1];//.replace("[\"","").replace("\"]","");
    }

    public String getTitleByPageNameFromApi(String pageName) {
        String pageId = getPageIdByNameFromApi(pageName);
        Response response = requestMethods.makeGetRequest(envConfig.getApiContentServiceUrl()+"/meta/?pageId="+pageId,requestHeaders,200);
        return JsonPath.read(response.asString(), "$.title").toString().split("\\{\\{")[0];
    }

    public ArrayList getCountriesFromApi(String tagGroup) {
        ArrayList allCountryList = new ArrayList<>();
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups?limit=800",
                requestHeaders, 200);
        String[] dataString = (JsonPath.read(response.asString(), "$.documents[?(@.name=='" + tagGroup + "')]..name[*]")).toString()
                .replace("[", "").replace("]", "")
                .split("\",\"");
        for(String countryName:dataString) {
            if(countryName.equals("BR1")||countryName.equals("pix")||countryName.equals("12")||countryName.equals("14")||
                    countryName.equals("133")||countryName.equals("ye1")||countryName.equals("ye2")||countryName.equals("ye5")||
                    countryName.equals("Deutch")||countryName.equals("land")){
                continue;}
            else{
                allCountryList.add(countryName.replace("\"", ""));
                if (countryName.equals("United States")) {
                    break;
                }
            }
        }
        allCountryList.add("All Countries");
        return allCountryList;
    }

    public String getTagGroupIdByName(String tagGroupName) {
        if (tagGroupName.equals("Licenses")) {
            tagGroupName = "Countries";
        }
        if (tagGroupName.equals("Others")) {
            tagGroupName = "Other products";
        }
        if (tagGroupName.equals("Payments")) {
            tagGroupName = "Payments ";
        }
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups?limit=800",
                requestHeaders, 200);
        return (JsonPath.read(response.asString(), "$.documents[?(@.name=='" + tagGroupName + "')].id")).toString().
                replace("\"", "").replace("[", "").replace("]", "");
    }

    public String getAgeRestrictionByCountryNameFromApi(String countryName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService()+"/tag-groups/"
                +getTagGroupIdByName("Countries"), requestHeaders, 200);
        return (JsonPath.read(response.asString(), "$.tags[?(@.name=='" + countryName + "')].props.age")).toString().replace("[", "").replace("]", "");
    }

    public String getAgeRestrictionByCountryNameFromApi(String countryName, String stateName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService()+"/tag-groups/"
                +getTagGroupIdByName("Countries"), requestHeaders, 200);
        return (JsonPath.read(response.asString(), "$.tags[?(@.name=='" + countryName + "')].tags[?(@.name=='" + stateName + "')].props.age")).toString().replace("[", "").replace("]", "");
    }
}
