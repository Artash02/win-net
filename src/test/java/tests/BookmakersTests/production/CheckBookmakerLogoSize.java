package tests.BookmakersTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBookmakerLogoSize extends BaseTest {

    @Test(description = "Check Bookmaker Logo Size")
    public void checkBookmakerLogoSize() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        Assert.assertEquals(reviewPage.getBookmakerLogoSize(), (float) 4.0);
    }
}
