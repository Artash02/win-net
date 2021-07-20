package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCorrectButtonIsActive extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "WN-151 Going from Comparison section to Full Review then coming back the page " +
            "should not navigate to the list section.")
    public void checkCorrectButtonIsActive() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertTrue(mainPage.waitToTitleContains("Esports betting sites | Customize your need and find the best bookmaker"),
                "'Esports betting sites | Customize your need and find the best bookmaker' expression was not found in Title");

        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        softAssert.assertTrue(mainPage.waitToTitleContains("@aaeeeee Winners.net"),"'@aaeeeee Winners.net' wasnot found in title.");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/review/"
                +BookmakerApiCalls.bookmakerName,baseUrl+"/review/"+BookmakerApiCalls.bookmakerName+" not found in URL.");

        mainPage.goToBackPage();
        softAssert.assertTrue(mainPage.waitToTitleContains("Esports betting sites | Customize your need and find the best bookmaker"),
                "'Esports betting sites | Customize your need and find the best bookmaker' expression not found in Title");
        softAssert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"'Comparison' button was not active!!!");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.refreshPage();
        softAssert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"true");

        mainPage.selectTabInHeader("Betting Tips");
        softAssert.assertTrue(mainPage.waitToTitleContains("Betting Tips - Winners.net")
                ,"'Analysis and Predictions - Winners.net' expression not found.");
        softAssert.assertEquals(mainPage.getPageUrl(), baseUrl+"/betting-tips"
                ,"Wrong Page!!!");

        mainPage.goToBackPage();
        softAssert.assertTrue(mainPage.waitToTitleContains("Esports betting sites | Customize your need and find the best bookmaker"),
                "'Esports betting sites | Customize your need and find the best bookmaker' expression not found in Title");
        softAssert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"true");
    }

    @AfterMethod(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}