package tests.RecommenderTests.testing;
import tests.base.BaseTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckBookmakerRecommenderSports extends BaseTest {

    @Test(description = "(WN-142, WN-215) Check bookmaker recommender functionality 'Sports' way")
    public void checkBookmakerRecommenderInSportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        softAssert.assertEquals(recommenderPage.getBookmakersCount()
                ,bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Armenia"),
                "filtering bookmakers count with Armenia country criteria failed");
        recommenderPage.selectCountryInRecommenderPage("Belarus");
        softAssert.assertEquals(recommenderPage.getBookmakersCount()
                ,bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Belarus"),
                "filtering bookmakers count with Belarus country criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Sports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount()
                , bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Belarus country and Sports criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Basketball");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Arrays.asList("Sports", "Basketball")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Belarus country and Sports(Basketball) criteria failed");
        recommenderPage.selectOthers("Cricket");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Arrays.asList("Sports", "Basketball", "Cricket")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Belarus country and Sports(Basketball, Cricket) criteria failed");
        recommenderPage.selectOthers("Valleyball");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Arrays.asList("Sports", "Basketball", "Cricket", "Valleyball")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Belarus country and Sports(Basketball, Cricket, Valleyball) criteria failed");
        recommenderPage.clickOnPreviousButton();
        recommenderPage.selectCheckbox("Sports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Belarus country criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("MasterCard");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "MasterCard"))), "filtering bookmakers count with Belarus country and Payments (MasterCard) criteria failed");
        recommenderPage.clickOnViewButton();
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), bookmakerApiCalls.getBookmakersNamesFromRecommenderApi("Belarus",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "MasterCard"))), "Filtered bookmakers name is not matched with bookmakers name from the list");
        softAssert.assertAll();
    }
}
