package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBonusComparisonButtonFunctionality extends BaseTest {

    @Test(description = "WN-114 Bookmakers full review checking with changed country and checking list and chart after that. ")
    public void checkBonusComparisonButtonFunctionality() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not selected!!!");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"))
                ,"'Find the best betting sites' expression - not found in header text");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        //need to be updated 
        softAssert.assertTrue(mainPage.waitToTitleContains("Betway Review"),"'Betway Review' text was not found in Title.");
        Assert.assertEquals(mainPage.getPageUrl(),baseUrl+"/review/betway","Url does not contain "+baseUrl+"/review/betway");

        //comparisonPage.clickOnBonusesButton();
        reviewPage.clickOnNavBarItem("Bonuses");

        softAssert.assertTrue(reviewPage.bookmakerNameInHeader ().contains("Betway")
                ,"'Betway' name was not displayed in header!!!");
        softAssert.assertTrue(reviewPage.compareWithOthersInHeader ().contains("COMPARE WITH OTHERS")
                ,"'COMPARE WITH OTHERS' name was not displayed in header!!!");
        softAssert.assertTrue(reviewPage.websideInHeader ().contains("WEBSITE")
                ,"'WEBSITE'name was not displayed in header!!!");

        reviewPage.clickOnBonusComparisonButton();

        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not displayed after 'Bonus Comparison' button click!!!");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("blue Betway")
                ,"'blue Betway' was not displayed in Chart-legend!!!");
        softAssert.assertTrue(bookmakersTablePage.valueInSortByDropDown().equals("Bonus Amount")
                ,"Wrong Filter was set!!!");

        softAssert.assertAll();
    }
}
