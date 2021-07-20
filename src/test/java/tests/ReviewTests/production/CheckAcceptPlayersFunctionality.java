package tests.ReviewTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckAcceptPlayersFunctionality extends BaseTest {

    @Test(description = "Check Accept players functionality")
    public void checkAcceptPlayersFunctionality() throws InterruptedException, IOException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        Assert.assertEquals(reviewPage.getBookmakerReviewPageTitle(), "Bet365 Review");
        Assert.assertEquals(reviewPage.getAcceptPlayers(),"Accept players from " + mainPage.getIpBasedCountry());
        reviewPage.clickOnNavBarItem("Basic Info");
        reviewPage.clickOnWebSiteUrl();
        mainPage.handleWindowByTitle("Open Account Offer");
        Assert.assertTrue(mainPage.urlContains("https://www.bet365.com/"), "Url does not contain 'https://www.bet365.com/' value");
    }
}
