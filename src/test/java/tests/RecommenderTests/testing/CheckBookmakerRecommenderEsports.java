package tests.RecommenderTests.testing;

import tests.base.BaseTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckBookmakerRecommenderEsports extends BaseTest {
    @Test(description = "(WN-140, WN-215) Check bookmaker recommender functionality 'Esports' way")
    public void checkBookmakerRecommenderInEsportsWay() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Armenia"),
                "filtering bookmakers count with Armenia country criteria failed");
        recommenderPage.selectCountryInRecommenderPage("Congo");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Congo"),
                "filtering bookmakers count with Congo country criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Esports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Collections.singletonList("Esports")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Congo country and esports criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("LoL");
        recommenderPage.selectOthers("Valorant");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Arrays.asList("Esports", "LoL", "Valorant")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Congo country and esports (LoL, Valorant) criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("VISA");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Arrays.asList("Esports", "LoL", "Valorant")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "VISA"))), "filtering bookmakers count with Congo country and esports (LoL, Valorant) and payments(VISA) criteria failed");
        recommenderPage.clickOnPreviousButton();
        recommenderPage.clickOnPreviousButton();
        recommenderPage.selectCheckbox("Esports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Collections.emptyList()), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "VISA"))), "filtering bookmakers count with Congo country and payments(VISA) criteria failed");
        recommenderPage.selectCheckbox("Esports");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectOthers("PUBG");
        recommenderPage.selectOthers("Valorant");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Arrays.asList("Esports", "PUBG", "Valorant")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "VISA"))), "filtering bookmakers count with Congo country and esports (pubg, Valorant) and payments(VISA) criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("VISA");
        recommenderPage.selectCheckbox("MasterCard");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(),bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Congo",
                new ArrayList<>(Arrays.asList("Esports", "PUBG", "Valorant")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "MasterCard"))), "filtering bookmakers count with Congo country and esports (pubg, Valorant) and payments(MasterCard) criteria failed");
        recommenderPage.clickOnViewButton();
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), bookmakerApiCalls.getBookmakersNamesFromRecommenderApi("Congo",
                new ArrayList<>(Arrays.asList("Esports", "PUBG", "Valorant")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Arrays.asList("Payments", "MasterCard"))), "Filtered bookmakers name is not matched with bookmakers name from the list");
        softAssert.assertAll();
    }
}
