package tests.ReviewTests.production;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckExcludedCountriesForBookmaker extends BaseTest {

    @Test(description = "Check Excluded Countries for Bookmaker")
    public void checkExcludedCountriesForBookmaker() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        Assert.assertTrue(mainPage.urlContains("betway"),"'Full Review' page was not displayed.");
        reviewPage.clickOnNavBarItem("Additional Info");
        reviewPage.clickOnShowMoreButtonByFieldNameTwo("Excluded countries");
        softAssert.assertEquals(reviewPage.getExcludedCountries(),
                bookmakerApiCalls.getApiListOfRestrictedCountriesByBookmakerName("Betway")
                ,"'Excluded Countries' list is not correct");
//  //TODO      reviewPage.clickOnShowMoreButtonByFieldName("Currency Accepted");

        reviewPage.clickOnShowMoreButtonByFieldNameTwo("Currency Accepted");
        softAssert.assertEquals(reviewPage.getCurrencies(), new ArrayList<>(Arrays.asList("USD","EUR","GBP","CAD","DKK",
            "CZK","HUF","ISK","NOK","SEK","PLN")), "'Currency Accepted' list is not correct");
    }
}
