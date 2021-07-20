package tests.FooterTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllBookmakersCountryNavigation extends BaseTest {
    @Test(description = "WN-285 : OK for all bookmakers country navigation")
    public void checkingIfCountryKeptAfterAllBookmakerPageNavigation() {
        filterPage.selectCountryInHeader("Albania");
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("All Bookmakers"));
        Assert.assertTrue(mainPage.urlContains("all-bookmakers"),
                "The all bookmakers page url is incorrect");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        Assert.assertEquals(filterPage.countryIs(), "Albania",
                "The country is wrong after all bookmakers page");
    }
}
