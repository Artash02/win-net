package api;

import com.jayway.jsonpath.JsonPath;
import config.AppConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import rest.RequestMethods;

import java.util.*;

public class ComparisonApiCalls {
    final AppConfig appConfig = ConfigFactory.create(AppConfig.class);
    final RequestMethods requestMethods = new RequestMethods();
    final Map<String, String> requestHeaders = new HashMap<>();

    public ComparisonApiCalls() {
        requestHeaders.put("Authorization", Authentication.token);
    }

    public List<Number> getRatingsFromApi(String bookmakerId) {

        List<Number> lstRat= new ArrayList<>();
        String[] services = new String[]{"overall", "bonus", "products", "responsibleGaming",
                "depositAndWithdrawal", "onboarding", "customerServices", "utility"};

        Response response = requestMethods.makeGetRequest(appConfig.getApiReviewsService() + "/bookmakers/"
                + bookmakerId,requestHeaders,200);
        for (String i:services) {
            lstRat.add(JsonPath.read(response.asString(), "$.reviews." + i + ".rating"));
        }
        return lstRat;
    }

    public int getTotalNumberOfBookmakersFromApi() {

        Response response = requestMethods.makeGetRequest(appConfig.getBookmakersService()+"/bookmakers?filter[0][type]=countries&filter[0][values][0][id]=5ede36d8f045090013da564&sort=-reviews.overall.rating&limit=60"
          ,requestHeaders,200); //All Countries = 5ede36d8f045090013da564
        int numberOfBookmakers=(JsonPath.read(response.asString(), "$.total"));
        System.out.println(numberOfBookmakers);
        return numberOfBookmakers;
    }

}