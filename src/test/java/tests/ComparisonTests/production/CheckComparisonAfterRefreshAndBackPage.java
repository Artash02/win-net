package tests.ComparisonTests.production;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckComparisonAfterRefreshAndBackPage extends BaseTest {

    @Test(description = "Check comparison chart when refreshing or back page")
    public void checkComparisonChartWhenRefreshingOrBackPage() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Plus");

        mainPage.refreshPage();

        mainPage.selectTabInHeader("Betting Tips");
        softAssert.assertTrue (mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"))
                ,"'Customize your need and find the best bookmaker' expression was not found in Title");
        Assert.assertTrue(mainPage.getPageUrl().contains(baseUrl+"/betting-tips"),"Url does not contain "
                +baseUrl+"/betting-tips");

        mainPage.goToBackPage();

        softAssert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"))
                ,"'Compare Betting Sites - Find the best option for you' was not found in title");
        Assert.assertTrue(mainPage.getPageUrl().contains(baseUrl+"/bookmaker-comparison")
                , baseUrl+"/bookmaker-comparison was not found in URL");

        softAssert.assertAll();
    }
}