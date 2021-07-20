package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCorrectButtonIsActive extends BaseTest {

    @Test(description = "WN-151 Going from Comparison section to Full Review then coming " +
            "back the page should not navigate to the list section.")
    public void checkCorrectButtonIsActive() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/bookmaker-comparison"
                ,baseUrl+"/bookmaker-comparison not found in URL.");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        //need update waitToTitleContains
        softAssert.assertTrue(mainPage.waitToTitleContains("Betway Review"),"'Betway Review' was not found in title.");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/review/betway"
                ,baseUrl+"/review/betway not found in URL.");

        mainPage.goToBackPage();
        softAssert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"'Comparison' button was not active!!!");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/bookmaker-comparison"
                ,baseUrl+"/bookmaker-comparison not found in URL.");

        comparisonPage.refreshPage();
        Assert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"'Comparison' button was not active!!!");

        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"))
                , "'Esports betting sites | Customize your need and find the best bookmaker' text not found.");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/betting-tips",
                baseUrl+"/betting-tips not found in URL.");

        mainPage.goToBackPage();

        softAssert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison")),
                "'Compare Betting Sites - Find the best option for you' expression not found in Title");
        Assert.assertEquals(mainPage.getPageUrl(), baseUrl+"/bookmaker-comparison"
            ,baseUrl+"/bookmaker-comparison not found in URL.");
        Assert.assertTrue(comparisonPage.checkIfButtonIsActive("Comparison"),"'Comparison' button was not active!!!");

        softAssert.assertAll();
    }
}