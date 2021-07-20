package tests.ReviewTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckClaimYourBonusButtonFunctionality extends BaseTest {

    @Test(description = "Check claim your bonus button functionality")
    public void checkClaimYourBonusButtonFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        reviewPage.clickOnClaimBonusButton();
        mainPage.handleWindowByTitle("Betway CA Sports offer");
        Assert.assertEquals(mainPage.getPageUrl(), "https://betway.com/bwp/sportsoffercan/en-ca/");
//        https://www.bet365.com/olp/open-account?affiliate=365_00942275
    }
}
