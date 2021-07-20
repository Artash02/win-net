package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckGamblingTherapyPage extends BaseTest {
    @Test(description = "WN-46 : (OK) Gambling therapy Page")
    public void checkGamblingTherapyIcon() {
        footerPage.ClickOnSocialPages("gamblingTherapy");
        mainPage.handleWindowByTitle("Gambling Therapy – Gambling Therapy");
        Assert.assertEquals(footerPage.getGamblingTherapyPageUrl(), "https://www.gamblingtherapy.org/en",
                "The gambling therapy page URL is wrong");
    }
}
