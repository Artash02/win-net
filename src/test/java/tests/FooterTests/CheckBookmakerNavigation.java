package tests.FooterTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBookmakerNavigation extends BaseTest {
    @Test(description = "WN-222 : (OK) Bookmaker filters on footer")
    public void checkBestBookmakersButtonsFromFooter() {
        footerPage.clickOnBookmakersSubMenuPages("Best Bookmakers");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Sports Betting Sites")),
                "Title is wrong and not equal Best Sports Betting Sites title");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Best Sports Betting Sites",
                "The header active component is not Best Sports Betting Sites");
        footerPage.clickOnBookmakersSubMenuPages("Best Esports Bookmakers");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Best Esports Betting Sites")),
                "Title is wrong and not equal Best Esports Betting Sites title");
        softAssert.assertEquals(footerPage.checkEsportsHeaderComponent(), "Best Esports Betting Sites",
                "The header active component is not Best Esports Betting Sites");
        footerPage.clickOnBookmakersSubMenuPages("Bookmaker Comparison");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison")),
                "Title is wrong and not equal Bookmaker comparison page title");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Bookmaker Comparison",
                "The header active component is not Bookmaker Comparison");
        footerPage.clickOnBookmakersSubMenuPages("Bookmaker Recommender");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Choose Your Bookmaker")),
                "Title is wrong and not equal Bookmaker recommender page title");
        softAssert.assertEquals(footerPage.checkHeaderActiveComponent(), "Bookmaker Recommender",
                "The header active component is not Bookmaker Recommender");
        softAssert.assertAll();
    }
}
