package tests.CleanURLsTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckCountryFilterNavigation extends BaseTest {
    @Test(description = "WN-228 : (OK) Country-filter navigation")
    public void checkHeaderFilterNavigation() throws InterruptedException {
        filterPage.selectCountryInHeader("Belgium");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Sports Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-sports-betting-sites?countries="+
                bookmakerApiCalls.getCountryIdByName("Belgium"),
                "The best sports betting sites page url is incorrect for Belgium");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")),
                "The sports covered filter selected elements is wrong");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertTrue(filterPage.getChartElement(), "Comparison chart isn't shown on Comparison page");
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/bookmaker-comparison?countries=" +
                bookmakerApiCalls.getCountryIdByName("Belgium"),
                "The bookmaker comparison page url is incorrect for Belgium");
        softAssert.assertTrue(filterPage.getChartElement(), "Comparison chart isn't shown on Comparison page after refresh");
        mainPage.goToBackPage();
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-sports-betting-sites?countries=" +
                bookmakerApiCalls.getCountryIdByName("Belgium"),
                "The URL is incorrect after going to the back page");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")),
                "The sports covered filter selected elements is wrong");
        softAssert.assertAll();
    }
}
