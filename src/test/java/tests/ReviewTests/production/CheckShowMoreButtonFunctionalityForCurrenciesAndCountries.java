package tests.ReviewTests.production;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckShowMoreButtonFunctionalityForCurrenciesAndCountries extends BaseTest {

    @Test(description = "Check show more buttons functionality for Currencies and Countries")
    public void checkShowMoreButtonFunctionalitiesForCurrenciesAndCountries() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        filterPage.selectCountryInHeader("All Countries");
        bookmakersTablePage.selectBookmakerFromBookmakersTable("Bet365");
        Assert.assertTrue(mainPage.urlContains("bet365"),"Bookmaker's page was not displayed.");
        reviewPage.clickOnNavBarItem("Additional Info");
        reviewPage.clickOnShowMoreButtonByFieldNameTwo("Excluded countries");
        System.out.println(reviewPage.getExcludedCountries());
        softAssert.assertEquals(reviewPage.getExcludedCountries(),
           bookmakerApiCalls.getApiListOfRestrictedCountriesByBookmakerName("Bet365")
                ,"'Excluded Countries' list is not correct");
        reviewPage.clickOnShowMoreButtonByFieldNameTwo("Currency Accepted");
        softAssert.assertEquals(reviewPage.getCurrencies(), new ArrayList<>(Arrays.asList("EUR", "USD", "GBP", "CAD",
                "NOK", "SEK", "AUD", "CHF", "DKK", "NZD", "CNY", "JPY", "BRL", "ARS", "MXN", "IDR", "THB")));
        softAssert.assertAll();
    }
}
