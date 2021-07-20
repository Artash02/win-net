package tests.BookmakersTests.production;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckOpenBookmakersWebSiteFromBookmakersTable extends BaseTest {

    @Test(description = "Open bookmakers site from bookmakers table")
    public void OpenBookmakersWebSiteFromBookmakersTable() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Website");
        mainPage.handleWindowByTitle("Betway CA Sports offer");
        Assert.assertTrue(mainPage.urlContains("https://betway.com"));

        mainPage.handleWindowByTitle("Compare Betting Sites - Winners.net");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Website");
        mainPage.handleWindowByTitle("Open Account Offer");
        Assert.assertTrue(mainPage.urlContains("https://www.bet365.com"));

        mainPage.handleWindowByTitle("Compare Betting Sites - Winners.net");
        bookmakersTablePage.clickOnButtonsByBookmakerName("1XBET", "Website");
        mainPage.handleWindowByTitle("First Deposit ⇒ 1xbet.com");
        Assert.assertTrue(mainPage.urlContains("https://onex50644.top"));

    }
}
