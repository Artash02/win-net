package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllCountriesShouldKeptAfterPageChanges extends BaseTest {
    //automation bug
    @Test(description = "WN-250 : (OK) All Countries should kept after page changes")
    public void checkingIfAllCountriesKeptAfterPageChanges() {
        filterPage.selectCountryInFilterSection("All Countries");
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/?countries=__all__&sort=-reviews.overall.rating",
                "The page URL is incorrect for All Countries country");
        footerPage.clickOnWinnersNetSubMenuPages("Contact");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Contact"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/contact",
                "The page URL is incorrect for contact page");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertEquals(filterPage.countryIs(), "All Countries",
                "The country isn't All Countries");
        softAssert.assertAll();
    }
}
