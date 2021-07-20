package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckFacebookPage extends BaseTest {
    @Test(description = "WN-42 : (OK) Facebook Official Page")
    public void checkFacebookIcon() {
        footerPage.clickOnSocialIcons("facebook");
        mainPage.handleWindowByTitle("Winners.Net - Home | Facebook");
        mainPage.switchToNewWindow();
        Assert.assertEquals(mainPage.getPageUrl(), "https://www.facebook.com/winnersnetgg",
                "The facebook winners.net page URL is wrong");
    }
}
