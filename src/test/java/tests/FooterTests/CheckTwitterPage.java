package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckTwitterPage extends BaseTest {
    @Test(description = "WN-43 : (OK) Twitter Official Page")
    public void checkTwitterIcon() throws InterruptedException {
        footerPage.clickOnSocialIcons("twitter");
        Thread.sleep(3000);
        mainPage.handleWindowByTitle("Winners.Net (@net_winners) / Twitter");
        Assert.assertEquals(footerPage.getWinnersNetTwitterPageUrl(), "https://twitter.com/net_winners",
                "The Twitter page URL is wrong");
    }
}
