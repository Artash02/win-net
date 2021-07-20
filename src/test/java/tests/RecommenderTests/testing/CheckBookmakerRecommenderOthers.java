package tests.RecommenderTests.testing;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckBookmakerRecommenderOthers extends BaseTest {

    @Test(description = "(WN-143, WN-215) Check bookmaker recommender functionality 'Casino' way")
    public void checkBookmakerRecommenderInSportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Armenia"),
                "filtering bookmakers count with Armenia country criteria failed");
        recommenderPage.selectCountryInRecommenderPage("Croatia");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Croatia"),
                "filtering bookmakers count with Croatia country criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Casino");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Arrays.asList("Others", "Casino")),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Croatia country and others (Casino) criteria failed");
        recommenderPage.selectOthers("Live Casino");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Arrays.asList("Others", "Casino", "Live Casino")),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Croatia country and others (Casino, Live Casino) criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Neteller");
        recommenderPage.selectOthers("Maestro");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Arrays.asList("Others", "Casino", "Live Casino")),
                new ArrayList<>(Arrays.asList("Payments", "Neteller", "Maestro"))), "filtering bookmakers count with Croatia country, others (Casino, Live Casino) and Payments (Neteller, Maestro) criteria failed");
        recommenderPage.clickOnPreviousButton();
        recommenderPage.selectCheckbox("Casino");
        recommenderPage.selectOthers("Live Casino");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "Neteller", "Maestro"))), "filtering bookmakers count with Croatia country and Payments (Neteller, Maestro) criteria failed");
        recommenderPage.selectOthers("Skin Betting");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Arrays.asList("Others", "Skin Betting")),
                new ArrayList<>(Arrays.asList("Payments", "Neteller", "Maestro"))), "filtering bookmakers count with Croatia country, others (Skin Betting) and Payments (Neteller, Maestro) criteria failed");
        recommenderPage.clickOnViewButton();
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), bookmakerApiCalls.getBookmakersNamesFromRecommenderApi("Croatia",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Arrays.asList("Others", "Skin Betting")),
                new ArrayList<>(Arrays.asList("Payments", "Neteller", "Maestro"))), "Filtered bookmakers name is not matched with bookmakers name from the list");
        softAssert.assertAll();
    }
}
