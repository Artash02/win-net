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

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookmakerApiCalls {
    public final String BOOKMAKER_IMAGE = "src/test/resources/bookmakerlogo.png";
    public final String RESOURCES_PATH = "src/test/resources/data/bookmaker/";
    public final String CREATE_BOOKMAKER = RESOURCES_PATH + "create-bookmaker.json";
    public final String CREATE_BOOKMAKER_IMAGE = RESOURCES_PATH + "upload-bookmaker-logo.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB = RESOURCES_PATH + "/profile/create-bookmaker-profile-tab.json";
    public final String CREATE_BOOKMAKER_BONUS_TAB = RESOURCES_PATH + "bonus/update-bookmaker-bonus-tab.json";
    public final String CREATE_MULTIPLE_BONUSES = RESOURCES_PATH + "bonus/create-multiple-bonuses.json";
    public final String BONUS_VALUE_SIX_DIGITS = RESOURCES_PATH + "bonus/bonus-value-six-digits.json";
    public final String BONUS_WITHOUT_COUNTRY = RESOURCES_PATH + "bonus/bonus-without-country.json";
    public final String BEST_BONUS = RESOURCES_PATH + "/bonus/best-bonus.json";
    public final String CREATE_BOOKMAKER_REVIEW_TAB = RESOURCES_PATH + "create-bookmaker-review-tab.json";
    public final String CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB = RESOURCES_PATH + "create-bookmaker-high-sports-review-tab.json";
    public final String CREATE_BOOKMAKER_SEO_TAB = RESOURCES_PATH + "create-bookmaker-seo-tab.json";
    public final String UPDATE_BOOKMAKER_SEO_TAB = RESOURCES_PATH + "update-bookmaker-seo-tab.json";
    public final String UPDATE_BOOKMAKER_PROFILE_TAB = RESOURCES_PATH + "/profile/update-bookmaker-profile-tab.json";
    public final String UPDATE_BOOKMAKER_FOOTERRANK_TAB = RESOURCES_PATH + "update-bookmaker-footerrank-tab.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_WITH_MINESOTA_RESTRICTED_COUNTRY = RESOURCES_PATH + "";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES = RESOURCES_PATH + "/profile/create-bookmaker-profile-tab-restricted-romania.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_NINETEEN_COUNTRIES_BY_NAMES = RESOURCES_PATH + "/profile/create-bookmaker-profile-tab-nineteen-restricted-countries.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_OVERVIEW_SPACE = RESOURCES_PATH + "/profile/create-bookmaker-profile-tab-space-ignoring.json";
    public final String CREATE_BOOKMAKER_PROFILE_TAB_WITHOUT_COMPANY_TERMSURL_YEAR = RESOURCES_PATH + "/profile/create-bookmaker-profile-tab-without-company-name-termsurl-year.json";

    public final String BOOKMAKER_PUBLISHING_TAB = RESOURCES_PATH + "update-bookmaker-publishing-tab.json";
    public final String UPDATE_BOOKMAKER_REVIEW_TAB = RESOURCES_PATH + "update-bookmaker-review-tab.json";
    public final String BOOKMAKER_PUBLISHING_TAB_WITH_SETTING_FOOTER_RANK = RESOURCES_PATH + "publish-bookmaker-with-rank.json";

    public final String ADD_BOOKMAKER_REVIEW_WITH_TWENTY_TWO_ESPORTS_TAGS = RESOURCES_PATH + "reviews/bookmaker-with-twenty-esport-tags.json";

    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_1 = RESOURCES_PATH + "reviews/review-tab-for-first-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_2 = RESOURCES_PATH + "reviews/review-tab-for-second-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_3 = RESOURCES_PATH + "reviews/review-tab-for-third-bookmaker-name.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_4 = RESOURCES_PATH + "reviews/review-tab-for-fourth-bookmaker.json";
    public final String REVIEW_TAB_FOR_BOOKMAKER_NAME_5 = RESOURCES_PATH + "reviews/review-tab-for-fifth-bookmaker.json";

    public final String SCORE_FOR_FIRST_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-first-bookmaker-case-score-is-three.json";
    public final String SCORE_FOR_SECOND_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-second-bookmaker-case-score-is-six.json";
    public final String SCORE_FOR_THIRD_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-third-bookmaker-case-score-is-eight.json";
//    public final String SCORE_FOR_FOURTH_BOOKMAKER = RESOURCES_PATH + "reviews/scores/review-tab-for-first-bookmaker-case-score-is-ten.json";

    public static final String bookmakerName = "selenium-bookmaker-" + System.currentTimeMillis();
    public static final String bookmaker_Name_1 = "selenium-bookmaker-1" + System.currentTimeMillis();
    public static final String bookmaker_Name_2 = "selenium-bookmaker-2" + System.currentTimeMillis();
    public static final String bookmaker_Name_3 = "selenium-bookmaker-3" + System.currentTimeMillis();
    public static final String bookmaker_Name_4 = "selenium-bookmaker-4" + System.currentTimeMillis();
    public static final String bookmaker_Name_5 = "selenium-bookmaker-5" + System.currentTimeMillis();
    public static final String bookmaker_Name_6 = "selenium-bookmaker-6" + System.currentTimeMillis();
    public static final String bookmaker_Name_7 = "selenium-bookmaker-7" + System.currentTimeMillis();
    public static final String bookmaker_Name_8 = "selenium-bookmaker-8" + System.currentTimeMillis();
    public static final String bookmaker_Name_9 = "selenium-bookmaker-9" + System.currentTimeMillis();
    public static final String bookmaker_Name_10 = "selenium-bookmaker-@" + System.currentTimeMillis();

    public static String bookmakerId;
    public static String bookmaker_Id_1;
    public static String bookmaker_Id_2;
    public static String bookmaker_Id_3;
    public static String bookmaker_Id_4;
    public static String bookmaker_Id_5;
    public static String bookmaker_Id_6;
    public static String bookmaker_Id_7;
    public static String bookmaker_Id_8;
    public static String bookmaker_Id_9;
    public static String bookmaker_Id_10;


    public String filePath;
    final Map<String, String> requestHeaders = new HashMap<>();
    final RequestMethods requestMethods = new RequestMethods();
    final RequestHelpers requestHelpers = new RequestHelpers();
    final AppConfig envConfig = ConfigFactory.create(AppConfig.class);

    public BookmakerApiCalls() {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public void createBookmaker() throws Exception {
        Log.warn(bookmakerName);
        Response response = requestMethods.makePostRequest(envConfig.getApiReviewsService() + "/bookmakers",
                setBookmakerName(CREATE_BOOKMAKER, bookmakerName), requestHeaders, 200);
        bookmakerId = (JsonPath.read(response.asString(), "$.document.id"));
        Log.warn(String.valueOf(bookmakerId));
    }

    public JSONObject setBookmakerName(String path, String bookmakerName) throws IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        jsonObject.replace("name", "bookmaker_name", bookmakerName);
        Log.warn(String.valueOf(jsonObject));
        return jsonObject;
    }

    public void uploadBookmakerLogo(String bookmakerId) throws Exception {
        File file = new File(BOOKMAKER_IMAGE);
        RequestSpecification requestPost = RestAssured.given().multiPart("image", file).
                multiPart("id", bookmakerId).multiPart("type", "bookmakers")
                .header("Authorization", Authentication.token);
        Log.warn("Bearer Token" + Authentication.token);
        Response response = requestPost.when().post(envConfig.getApiFilesServiceUrl() + "/images/file");
        Log.warn("Crop Call" + response.getBody().asString());
        requestHelpers.checkResponseStatusCode(response, 200);
        filePath = JsonPath.read(response.asString(), "filePath");
        RequestSpecification requestPut = RestAssured.given().contentType(ContentType.JSON).header("Authorization",
                Authentication.token);
        Log.warn(String.valueOf(bookmakerId));
        Response response1 = requestPut.when().body(getBookmakerImageFilePathBody(CREATE_BOOKMAKER_IMAGE, filePath))
                .put(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId);
        Log.warn(response1.getBody().asString());
    }

    //  common method
    public JSONObject getBookmakerImageFilePathBody(String path, String filePath) throws
            IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        JSONObject images = (JSONObject) jsonObject.get("images");
        images.put("logo", filePath);
        return jsonObject;
    }

    public void addBookmakerBonus(String bookmakerId) throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(CREATE_BOOKMAKER_BONUS_TAB), requestHeaders, 200);
    }

    public void addMultipleBonuses(String bookmakerId) throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(CREATE_MULTIPLE_BONUSES), requestHeaders, 200);
    }

    public void addBestBonus(String bookmakerId) throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(BEST_BONUS), requestHeaders, 200);
    }

    public String addIncorrectBookmakerBonusValue(String bookmakerId) throws Exception {
        return requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(BONUS_VALUE_SIX_DIGITS), requestHeaders, 200).getBody().asString();
    }

    public void addBookmakerWithTwentyTwoEsportTags(String bookmakerId) throws Exception {
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(ADD_BOOKMAKER_REVIEW_WITH_TWENTY_TWO_ESPORTS_TAGS), requestHeaders, 200);
    }

    public void addReviewsForSorting(String bookmakerId, String bookmakerName) throws Exception {
        String reviewBody = null;
        if (bookmakerName.equals(bookmaker_Name_1)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_1;
        }
        if (bookmakerName.equals(bookmaker_Name_2)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_2;
        }
        if (bookmakerName.equals(bookmaker_Name_3)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_3;
        }
        if (bookmakerName.equals(bookmaker_Name_4)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_4;
        }
        if (bookmakerName.equals(bookmaker_Name_5)) {
            reviewBody = REVIEW_TAB_FOR_BOOKMAKER_NAME_5;
        }
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/reviews",
                requestHelpers.getRequestBody(reviewBody), requestHeaders, 200);
    }

    public String changeBookmakerStatus(String bookmakerId, String value) throws Exception {
        return requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/status",
                setBookmakerStatus(BOOKMAKER_PUBLISHING_TAB, value), requestHeaders, 200).getBody().asString();
    }

    public JSONObject setBookmakerStatus(String path, String value) throws IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        jsonObject.put("status", value);
        return jsonObject;
    }

    public JSONObject setBookmakerStatusAndFooterRank(String path, String value, int rank) throws IOException, ParseException {
        JSONObject jsonObject = requestHelpers.getRequestBody(path);
        jsonObject.put("status", value);
        jsonObject.put("footerRank", rank);
        return jsonObject;
    }

    public void changeBookmakerPublishStatus(String bookmakerId, String status) throws IOException, ParseException {
        requestMethods.makePutRequest(envConfig.getApiReviewsService()+ "/bookmakers/" + bookmakerId + "/status",
                setBookmakerStatus(BOOKMAKER_PUBLISHING_TAB, status),
                requestHeaders, 200);
    }

    public String changeBookmakerStatusWithIncorrectRating(String bookmakerId, String value) throws Exception {
        Thread.sleep(3000);
        return requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + "/status",
                setBookmakerStatus(BOOKMAKER_PUBLISHING_TAB, value), requestHeaders, 409).getBody().asString();
    }

    public void addBookmakerFooterRank(String bookmakerId,String status, int rank) throws IOException, ParseException {
        requestMethods.makePutRequest(envConfig.getApiReviewsService()+ "/bookmakers/" + bookmakerId + "/status",
                setBookmakerStatusAndFooterRank(BOOKMAKER_PUBLISHING_TAB_WITH_SETTING_FOOTER_RANK, status, rank),
                requestHeaders, 200);
    }

    public void deleteBookmakerById(String bookmakerId) {
        requestMethods.makeDeleteRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId, requestHeaders, 204);
    }

     public void createNBookmakers(String[] args) throws Exception {

        int cnt = 0;
        for (String arg : args) {
            Response response = requestMethods.makePostRequest(envConfig.getApiReviewsService() + "/bookmakers/",
                    setBookmakerName(CREATE_BOOKMAKER, arg), requestHeaders, 200);
            String idBookmaker = (JsonPath.read(response.asString(), "$.document.id"));
            ++cnt;
            switch (cnt) {
                case 1 -> {
                    bookmaker_Id_1 = idBookmaker;
                    Log.warn("first bookmaker id " + bookmaker_Id_1);
                    Log.warn("first bookmaker name " + arg);
                }
                case 2 -> {
                    bookmaker_Id_2 = idBookmaker;
                    Log.warn("second bookmaker id " + bookmaker_Id_2);
                    Log.warn("second bookmaker name " + arg);
                }
                case 3 -> {
                    bookmaker_Id_3 = idBookmaker;
                    Log.warn("third bookmaker id " + bookmaker_Id_3);
                    Log.warn("third bookmaker name " + arg);
                }
                case 4 -> {
                    bookmaker_Id_4 = idBookmaker;
                    Log.warn("fourth bookmaker id " + bookmaker_Id_4);
                    Log.warn("fourth bookmaker name " + arg);
                }
                case 5 -> {
                    bookmaker_Id_5 = idBookmaker;
                    Log.warn("fifth bookmaker id " + bookmaker_Id_5);
                    Log.warn("fifth bookmaker name " + arg);
                }
                case 6 -> {
                    bookmaker_Id_6 = idBookmaker;
                    Log.warn("sixth bookmaker id " + bookmaker_Id_6);
                    Log.warn("sixth bookmaker name " + arg);
                }
                case 7 -> {
                    bookmaker_Id_7 = idBookmaker;
                    Log.warn("seventh bookmaker id " + bookmaker_Id_7);
                    Log.warn("seventh bookmaker name " + arg);
                }
                case 8 -> {
                    bookmaker_Id_8 = idBookmaker;
                    Log.warn("eighth bookmaker id " + bookmaker_Id_8);
                    Log.warn("eighth bookmaker name " + arg);
                }
                case 9 -> {
                    bookmaker_Id_9 = idBookmaker;
                    Log.warn("ninth bookmaker id " + bookmaker_Id_9);
                    Log.warn("ninth bookmaker name " + arg);
                }
                case 10 -> {
                    bookmaker_Id_10 = idBookmaker;
                    Log.warn("tenth bookmaker id " + bookmaker_Id_10);
                    Log.warn("tenth bookmaker name " + arg);
                }
            }
        }
    }

    public int getBookmakersCount() {
        Response response = requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers", requestHeaders, 200);
        return (JsonPath.read(response.asString(), "$.total"));
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

    public String getTagIdByTagGroupNameAndTagName(String tagGroupName, String tagName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups/" + getTagGroupIdByName(tagGroupName.trim()),
                requestHeaders, 200);
        return (JsonPath.read(response.asString(), "$.tags[?(@.name=='" + tagName + "')].id")).toString().
                replace("\"", "").replace("[", "").replace("]", "");
    }

    public Response getBookmakersFromApiByCountryAndFilterCriteria(String filterCriteria, String
            filterValue, String countryName) {
        return requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers?filter[0][type]=" +
                "countries&filter[0][values][0][id]=" + getCountryIdByName(countryName) + "&filter[1][type]=" + filterCriteria.toLowerCase().trim() + "&filter[1][values][0][id]="
                + getTagIdByTagGroupNameAndTagName(filterCriteria, filterValue), requestHeaders, 200);
    }

    public int getBookmakersCountFromApiByCountryAndFilterCriteria(String filterCriteria, String
            filterValue, String countryName) {
        return (JsonPath.read(getBookmakersFromApiByCountryAndFilterCriteria(filterCriteria, filterValue, countryName).asString(), "$.total"));
    }


    public String getCountryIdByName(String countryName) {
        Response tagsResponse = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups/5ece82f0053031001047ca5b", requestHeaders, 200);
        return (JsonPath.read(tagsResponse.asString(), "$.tags[?(@.name ==" + "'" + countryName + "'" + ")].id")).toString().
                replace("\"", "").replace("[", "").replace("]", "");
    }

    public Response getBookmakersFromApiByCountryAndFilterCriteriaCashout(String filterCriteria, String
            filterValue, String countryName) {
        return requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers?filter[0][type]="
                + filterCriteria + "&filter[0][values][0]=" + filterValue + "&filter[1][type]=countries&filter[1][values][0][id]="
                + getCountryIdByName(countryName), requestHeaders, 200);
    }

    public int getBookmakersCountFromApiByCountryAndFilterCriteriaCashout(String filterCriteria, String
            filterValue, String countryName) {
        return (JsonPath.read(getBookmakersFromApiByCountryAndFilterCriteriaCashout(filterCriteria, filterValue, countryName).asString(), "$.total"));
    }

    public Response getBookmakersOnRecommenderPageFromApiByCountry(String countryName) {
        return requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers?filter[3][type]=countries&filter[3][values][0][id]="
                + getCountryIdByName(countryName), requestHeaders, 200);
    }

    public int getBookmakersCountOnRecommenderPageFromApiByCountry(String countryName) {
        return (JsonPath.read(getBookmakersOnRecommenderPageFromApiByCountry(countryName).asString(), "$.total"));
    }

    public Response getBookmakersOnRecommenderPageFromApiByCountryAndTagGroupAndTag(String
                                                                                            countryName, ArrayList<String> esportsArr, ArrayList<String> sportsArr, ArrayList<String> othersArr, ArrayList<String> paymentsArr) {
        String apiFilter = "";
        if (esportsArr.size() == 1) {
            apiFilter += "&filter[0][type]=esports&filter[0][values][0][id]=__any__";
        } else if (esportsArr.size() > 1) {
            apiFilter += "&filter[0][type]=esports";
            for (int i = 1; i < esportsArr.size(); i++) {
                apiFilter += "&filter[0][values][" + (i - 1) + "][id]=" + getTagIdByTagGroupNameAndTagName(esportsArr.get(0), esportsArr.get(i));
            }
        }
        if (sportsArr.size() == 1) {
            apiFilter += "&filter[1][type]=sports&filter[1][values][0][id]=__any__";
        } else if (sportsArr.size() > 1) {
            apiFilter += "&filter[1][type]=sports";
            for (int i = 1; i < sportsArr.size(); i++) {
                apiFilter += "&filter[1][values][" + (i - 1) + "][id]=" + getTagIdByTagGroupNameAndTagName(sportsArr.get(0), sportsArr.get(i));
            }
        }
        if (othersArr.size() > 1) {
            apiFilter += "&filter[4][type]=others";
            for (int i = 1; i < othersArr.size(); i++) {
                apiFilter += "&filter[4][values][" + (i - 1) + "][id]=" + getTagIdByTagGroupNameAndTagName(othersArr.get(0), othersArr.get(i));
            }
        }
        if (paymentsArr.size() > 1) {
            apiFilter += "&filter[2][type]=payments";
            for (int i = 1; i < paymentsArr.size(); i++) {
                apiFilter += "&filter[2][values][" + (i - 1) + "][id]=" + getTagIdByTagGroupNameAndTagName(paymentsArr.get(0), paymentsArr.get(i));
            }
        }
        return requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers?filter[3][type]=countries&filter[3][values][0][id]="
                + getCountryIdByName(countryName) + apiFilter + "&sort=-reviews.overall.rating&limit=60", requestHeaders, 200);
    }

    public int getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag(String
                                                                                            countryName, ArrayList<String> esportsArr,
                                                                                    ArrayList<String> sportsArr, ArrayList<String> othersArr, ArrayList<String> paymentsArr) {
        return (JsonPath.read(getBookmakersOnRecommenderPageFromApiByCountryAndTagGroupAndTag(countryName, esportsArr, sportsArr, othersArr, paymentsArr).asString(), "$.total"));
    }

    public List<String> getBookmakersNamesFromRecommenderApi(String  countryName, ArrayList<String> esportsArr, ArrayList<String> sportsArr, ArrayList<String> othersArr, ArrayList<String> paymentsArr) {
        List<String> names = JsonPath.read(getBookmakersOnRecommenderPageFromApiByCountryAndTagGroupAndTag(countryName, esportsArr, sportsArr, othersArr, paymentsArr).asString(), "$.documents[*].name");
        Collections.sort(names);
        return names;
    }

    public List<String> getBookmakersNamesByCountryAndFilterCriteriaCashout(String filterCriteria, String
            filterValue, String countryName) {
        List<String> names = JsonPath.read(getBookmakersFromApiByCountryAndFilterCriteriaCashout(filterCriteria, filterValue, countryName).asString(), "$.documents[*].name");
        Collections.sort(names);
        return names;
    }

    public List<String> getBookmakersNamesByCountryAndFilterCriteria(String filterCriteria, String
            filterValue, String countryName) {
        ArrayList<Object> listdata = new ArrayList<Object>();
        List<String> names = JsonPath.read(getBookmakersFromApiByCountryAndFilterCriteria(filterCriteria, filterValue, countryName).asString(), "$.documents[*].name");
        Collections.sort(names);
        return names;
    }

    public ArrayList getRatingsFromApiByBookmakerName(String args, Response response) {

        List lst = (JsonPath.read(response.asString(), "$.documents"));

        ArrayList lstResult = new ArrayList();

        for (Object i : lst) {
            if (i.toString().contains("name=" + args + ",")) {
                String[] z = i.toString().split(",");
                for (String j : z) {
                    if (j.contains("rating")) {
                        j = j.replace("{", "")
                                .replace(" ", "")
                                .replace("}", "")
                                .replace("=", "")
                                .replace("reviews", "")
                                .replace("rating", "")
                                .replace("overall", "")
                                .replace("products", "")
                                .replace("responsibleGaming", "")
                                .replace("depositAndWithdrawal", "")
                                .replace("onboarding", "")
                                .replace("customerServices", "")
                                .replace("utility", "");
                        lstResult.add(j);
                    }
                }
                break;
            }
        }
        return lstResult;
    }

    public Response getBookmakersDataFromApi() {
        Response response = requestMethods.makeGetRequest(envConfig.getBookmakersService() + "/bookmakers/"
                        + "?filter[0][type]=countries&filter[0][values][0][id]=5ede36d8f045090013da564&sort=-reviews.overall.rating&limit=100"
                , requestHeaders, 200);
        return response;
    }

    public ArrayList<Object> getApiListOfRestrictedCountriesByBookmakerName(String bookmakerName) {

        ArrayList<Object> listOfRestrictedCountries = new ArrayList<Object>();
        Response response = requestMethods.makeGetRequest(envConfig.getBaseUrl() + "/_next/" +
                "data/d45260264f9013757526468977a50ec347463b73" + "/review/" + bookmakerName.toLowerCase() + ".json?slug=" + bookmakerName.toLowerCase() +
                getBookmakerIdFromApi(bookmakerName), requestHeaders, 200);
        listOfRestrictedCountries = JsonPath.read(response.asString(), "$.pageProps.initialState.bookmaker.bookmakerItem.countries.restricted.tags[*].name");
        return listOfRestrictedCountries;
    }
//If the statement below is put at 507-509 then getBookmakerIdFromApi(String bookmakerName) methode can be ignored.
// Response response = requestMethods.makeGetRequest("https://winners.net/_next/data/d45260264f9013757526468977a50ec347463b73" +
//                "/review/"+bookmakerName.toLowerCase()+".json?slug="+bookmakerName.toLowerCase()+"\"", requestHeaders, 200);

    public String getBookmakerIdFromApi(String bookmakerName) {

        Object BookmakerIdFromApi = (JsonPath.read(getBookmakersDataFromApi().asString(), "$.documents[?(@.name==" + bookmakerName + ")].id"));
        return String.valueOf(BookmakerIdFromApi);
    }

    public String getBookmakerSeoTitleFromApi(String bookmakerName) {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/bookmakers/"
                + getBookmakerIdFromApi(bookmakerName).split("\"")[1], requestHeaders, 200);
        return JsonPath.read(response.asString(), "$.seo.title");
    }

    public JSONObject getRequestBodyUsingTagNamesInsteadOfTagIdsFromJsonFile(String path) throws IOException, ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        ArrayList<String> tagList = getTagNames();
        ArrayList<String> tagIdList = getTagIdsList();//getTagIdByTagName(tagList);

        String jsonContentVar = "";
        JSONParser jsonParser = new JSONParser();
        File myObj = new File(path);
        Scanner reader = new Scanner(myObj);
        while (reader.hasNextLine()) {
            jsonContentVar = jsonContentVar + reader.nextLine();
        }
        for (int i = 0; i < tagIdList.size(); i++) {
            if (tagList.get(i).equals("12") || tagList.get(i).equals("14") || tagList.get(i).equals("133")) {
            } else {
                 jsonContentVar = jsonContentVar.replace("\""+tagList.get(i)+"\"", "\""+tagIdList.get(i)+"\"");
            }
        }
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonContentVar);
        return jsonObject;
    }

    public ArrayList<String> getTagNames() {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups/?limit=1000",
                requestHeaders, 200);
        return (JsonPath.read(response.getBody().asString(), "$..tags[*].name"));
    }

    public ArrayList<String> getTagIdByTagName(ArrayList<String> tagList) {
        ArrayList<String> idList = new ArrayList();
        Response tagsResponse = requestMethods.makeGetRequest(envConfig.getApiReviewsService()
                + "/tag-groups?limit=1000", requestHeaders, 200);

        for (String tag : tagList) {
            if (tag.contains("'")) {
                tag="apostrophe";
            }
            String tagId = (JsonPath.read(tagsResponse.asString(), "$.documents[*].tags[?(@.name=='" + tag + "')].id").toString()
                    .replace("\"", "").replace("[", "").replace("]", ""));
            if (tagId.contains(",")) {
                if (idList.contains(tagId.substring(0, (tagId.length() - 1) / 2))) {
                    tagId = tagId.substring((tagId.length() - 1) / 2 + 1, tagId.length());
                } else {
                    tagId = tagId.substring(0, (tagId.length() - 1) / 2);
                }
            }
            idList.add(tagId);
        }

        return idList;
    }

    public void addBookmakerData(String requestUrlSuffix, String bookmakerId, String jsonPath) throws Exception {
        if (requestUrlSuffix.equals("profile") || requestUrlSuffix.equals("")) {
            requestUrlSuffix = "";
        } else {
            requestUrlSuffix = "/" + requestUrlSuffix;
        }
        requestMethods.makePutRequest(envConfig.getApiReviewsService() + "/bookmakers/" + bookmakerId + requestUrlSuffix,
                getRequestBodyUsingTagNamesInsteadOfTagIdsFromJsonFile(jsonPath), requestHeaders, 200);
    }

    public int getQuickCardsCountFromApiByCountry(String countryName) {
        if (countryName != null) {
            Response response = requestMethods.makeGetRequest("https://api-bookmakers.priotix.xyz/bookmakers/best?" +
                            "filter[0][type]=countries&filter[0][values][0][id]=" + getCountryIdByName(countryName), requestHeaders, 200);
            return (JsonPath.read(response.asString(), "$..quickCard.rank.length()"));
        }
        else {
            Response response = requestMethods.makeGetRequest("https://api-bookmakers.priotix.xyz/bookmakers/best",
                    requestHeaders, 200);
            return (JsonPath.read(response.asString(), "$..quickCard.length()"));
        }
    }
    public ArrayList<String> getTagIdsList() {
        Response response = requestMethods.makeGetRequest(envConfig.getApiReviewsService() + "/tag-groups/?limit=1000",
                requestHeaders, 200);
        return (JsonPath.read(response.getBody().asString(), "$..tags[*].id"));

    }
}

