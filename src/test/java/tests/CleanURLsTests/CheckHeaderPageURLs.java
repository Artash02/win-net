package tests.CleanURLsTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;


public class CheckHeaderPageURLs extends BaseTest {
    @Test(description = "WN-146 : (OK) Header/Footer URL")
    public void checkHeaderPagesURLs() {
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/",
                "The homepage URL is incorrect");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Sports Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() +"/best-sports-betting-sites",
                "The Best Sports Betting Sites URL is incorrect");
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Esports Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() + "/best-esports-betting-sites",
                "The Best Esports Betting Sites URL is incorrect");
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best LoL Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-lol-betting-sites",
                "The homepage URL is incorrect");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/bookmaker-comparison",
                "The Best LoL Betting Sites URL is incorrect");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best CSGO Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-csgo-betting-sites",
                "The Best CSGO Betting Sites URL is incorrect");
        mainPage.selectTabInHeader("Betting Tips");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/betting-tips",
                "The Betting Tips URL is incorrect");
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Dota2 Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-dota2-betting-sites",
                "The Best Dota2 Betting Sites URL is incorrect");
        softAssert.assertAll();
    }
}
