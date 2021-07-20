package tests.RecommenderTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckBookmakerRecommenderAll extends BaseTest {
//    @BeforeMethod(description = "Create two bookmakers")
//    public void createBookmakers() throws Exception {
//        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2};
//        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
//        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
//        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_1
//                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
//        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
//                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
//        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
//                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
//        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1, "published");
//
//        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
//        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_2
//                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
//        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
//                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
//        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
//                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
//        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2, "published");
//    }
    @Test(description = "(WN-139, WN-215) Check bookmaker recommender functionality 'Casino', 'Esports', 'Sports' way")
    public void checkBookmakerRecommenderInAllWays() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Armenia"),
                "filtering bookmakers count with Armenia country criteria failed");
        recommenderPage.selectCountryInRecommenderPage("Bahamas");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountry("Bahamas"),
                "filtering bookmakers count with Bahamas country criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Esports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Collections.singletonList("Esports")), new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList()),new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Bahamas country and esports criteria failed");
        recommenderPage.selectCheckbox("Sports");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Collections.singletonList("Esports")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Collections.emptyList()),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports criteria failed");
        recommenderPage.selectCheckbox("Casino");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Collections.singletonList("Esports")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Arrays.asList("Others", "Casino")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports and casino criteria failed");
        recommenderPage.selectOthers("Skin Betting");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Collections.singletonList("Esports")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports and casino, skin betting criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Dota 2");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports (Dota2) and casino, skin betting criteria failed");
        recommenderPage.selectCheckbox("CS:GO");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports (Dota 2, CS:GO) and casino, skin betting criteria failed");
        recommenderPage.selectCheckbox("LoL");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Collections.singletonList("Sports")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports, esports (Dota 2, CS:GO, LoL) and casino, skin betting criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("Football");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Arrays.asList("Sports", "Football")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports (Football), esports (Dota 2, CS:GO, LoL) and casino, skin betting criteria failed");
        recommenderPage.selectCheckbox("Tennis");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis")), new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")),
                new ArrayList<>(Collections.emptyList())), "filtering bookmakers count with Bahamas country and sports (Football, Tennis), esports (Dota 2, CS:GO, LoL) and casino, skin betting criteria failed");
        recommenderPage.selectCheckbox("Basketball");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis", "Basketball")),
                new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")), new ArrayList<>(Collections.emptyList())),
                "filtering bookmakers count with Bahamas country and sports (Football, Tennis, Basketball), esports (Dota 2, CS:GO, LoL) and casino, skin betting criteria failed");
        recommenderPage.clickOnNextButton();
        recommenderPage.selectCheckbox("VISA");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis", "Basketball")),
                new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")), new ArrayList<>(Arrays.asList("Payments", "VISA"))),
                "filtering bookmakers count with Bahamas country and sports (Football, Tennis, Basketball), esports (Dota 2, CS:GO, LoL), casino, skin betting and payments (VISA) criteria failed");
        recommenderPage.selectCheckbox("Neteller");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL")), new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis", "Basketball")),
                new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting")), new ArrayList<>(Arrays.asList("Payments", "VISA", "Neteller"))),
                "filtering bookmakers count with Bahamas country and sports (Football, Tennis, Basketball), esports (Dota 2, CS:GO, LoL), casino, skin betting and payments (VISA, Neteller) criteria failed");
        recommenderPage.selectCheckbox("MasterCard");
        softAssert.assertEquals(recommenderPage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCountOnRecommenderPageFromApiByCountryAndTagGroupAndTag("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL"))
                , new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis", "Basketball")),
                new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting"))
                , new ArrayList<>(Arrays.asList("Payments", "VISA", "Neteller", "MasterCard"))),
                "filtering bookmakers count with Bahamas country and sports (Football, Tennis, Basketball), esports (Dota 2, CS:GO, LoL), casino, skin betting and payments (VISA, Neteller, MasterCard) criteria failed");
        recommenderPage.clickOnViewButton();
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName()
                , bookmakerApiCalls.getBookmakersNamesFromRecommenderApi("Bahamas",
                new ArrayList<>(Arrays.asList("Esports", "Dota 2", "CS:GO", "LoL"))
                        , new ArrayList<>(Arrays.asList("Sports", "Football", "Tennis", "Basketball")),
                new ArrayList<>(Arrays.asList("Others", "Casino", "Skin Betting"))
                        , new ArrayList<>(Arrays.asList("Payments", "VISA", "Neteller", "MasterCard"))),
                "Filtered bookmakers name does not match with bookmakers name from the list");
        softAssert.assertAll();
    }
//    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
//    public void deleteCreatedBookmakers() {
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
//        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
//    }
}
