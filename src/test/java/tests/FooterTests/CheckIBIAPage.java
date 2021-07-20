package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckIBIAPage extends BaseTest {
    @Test(description = "WN-45 : (OK) International betting integrity association Page")
    public void checkIbiaIcon() {
        footerPage.ClickOnSocialPages("ibia");
        mainPage.handleWindowByTitle("Homepage new | IBIA");
        Assert.assertEquals(footerPage.getIbiaPageUrl(), "https://ibia.bet/",
                "The IBIA site URL is wrong");
    }
}
