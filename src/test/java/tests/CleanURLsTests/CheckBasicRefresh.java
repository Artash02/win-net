package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBasicRefresh extends BaseTest {
    @Test(description = "WN-244 : (OK) Basic refresh")
    public void checkBasicRefresh() {
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(), "Armenia", "The country is not Armenia");
        filterPage.selectCountryInHeader("All Countries");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(), "All Countries",
                "After refresh the 'All Countries' country isn't kept");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.countryIs(), "All Countries",
                "After page change and refresh the 'All Countries' isn't kept");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/bookmaker-comparison?countries=__all__",
                "The page url is incorrect for bookmaker comparison page for 'All Countries'");
        softAssert.assertAll();
    }
}
