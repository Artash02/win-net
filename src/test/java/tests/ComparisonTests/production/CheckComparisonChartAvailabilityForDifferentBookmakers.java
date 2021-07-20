package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckComparisonChartAvailabilityForDifferentBookmakers extends BaseTest {

    @Test(description = "WN-132 Checks comparison chart ability when bookmaker deleting in bookmakers list.")
    public void checkComparisonChartAvailabilityForDifferentBookmakers() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not selected!!!");

        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), comparisonApiCalls
                .getTotalNumberOfBookmakersFromApi(),"Numbers of bookmakers Do NOT MATCH");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(mainPage.getPageUrl().contains(baseUrl+"/bookmaker-comparison")
                ,baseUrl+"/bookmaker-comparison not found in URL.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(),comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                ,"Numbers of bookmakers Do NOT MATCH after Comparison button click");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Midnite","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("GG.BET","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("BetMGM","Plus");

        filterPage.selectCountryInHeader("Armenia");

        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(1).contains("Midnite")
                ,"Chart for 'Midnite' bookmaker should not be among Armenia's bookmakers!!!");
        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(1).contains("BETMGM")
                ,"Chart for 'BETMGM' bookmaker should not be among Armenia's bookmakers!!!");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("blue GG.BET")
                ,"Chart for 'blue GG.BET' is not displayed!!!");//rgb(9, 105, 250)GG.BET: GG.BET in blue (250)

        softAssert.assertAll();
    }
}
