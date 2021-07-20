package tests.FooterTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckContactPageContent extends BaseTest {
    @Test(description = "WN-16 : (OK) Contact Page, WN-146 : (OK) Header/Footer URL")
    public void checkContactPage() {
        footerPage.clickOnWinnersNetSubMenuPages("Contact");
        Assert.assertTrue(mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Contact")),
                "Title doesn't contain 'Contact Us' page value.");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Contact", "The page subtitle not equal 'Contact'");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/contact",
                "The Contact page URL is wrong");
        softAssert.assertEquals(footerPage.getContactPageInfo(), new ArrayList<>(Arrays.asList("+18332222946",
                "help@winners.net", "120 South 6th St, Suite 900, Minneapolis, MN 55402")),
                "Contact page info is incorrect");
        softAssert.assertAll();
    }
}
