package tests.CleanURLsTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCleanUpURL extends BaseTest {
    @Test(description = "WN-221 : (OK) Clean-up URL")
    public void URLIsCleanAfterBackNavigation() {
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl()+"/",
                "The homepage URL is incorrect");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Sports Betting Sites"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/best-sports-betting-sites",
                "The best sports betting sites page URL is incorrect");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl()+"/",
                "The homepage URL is incorrect after going to browser back page");
        softAssert.assertAll();
    }
}
