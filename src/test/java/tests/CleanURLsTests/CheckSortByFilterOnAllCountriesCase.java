package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;


public class CheckSortByFilterOnAllCountriesCase extends BaseTest {
    @Test(description = "WN-252 : (OK) Sort-by filter on All countries case")
    public void checkSortByFilter() throws InterruptedException {
        filterPage.selectCountryInHeader("All Countries");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/?countries=__all__&sort=-reviews.overall.rating",
                "The URL is wrong for sorting by rating case");
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/?countries=__all__&sort=name",
                "The URL is wrong for sorting by bookmaker name case");
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/?countries=__all__?",
                "The homepage URL is wrong");
        softAssert.assertAll();
    }
}
