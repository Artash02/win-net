package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckStatesAfterPageChanges extends BaseTest {
    @Test(description = "WN-251 : (OK) States after page changes")
    public void checkStatesAfterNavigation()  {
        filterPage.selectCountryInHeader("United States");
        filterPage.selectCountryStateInHeader("Minnesota");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertEquals(filterPage.countryIs(), "United States", "The country is not US");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota", "The state is not Minnesota");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best CSGO Betting Sites"));
        softAssert.assertEquals(filterPage.countryIs(), "United States", "The country is not US after page change");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota", "The state is not Minnesota after page change");
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.countryIs(), "United States", "The country is not US after refresh");
        softAssert.assertEquals(filterPage.stateIs(), "Minnesota", "The state is not Minnesota after refresh");
        softAssert.assertAll();
    }
}
