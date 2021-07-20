package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckNewsletterAlreadySubscribed extends BaseTest {
    @Test(description = "WN-214 : (NOK) Newsletter check") // manual bug
    public void checkNewsLetterExistingEmail() {
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        footerPage.newsletterInput("test@gmail.com");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterSuccessText(),"Subscription successful!",
                "Validation message is wrong for success case");
        mainPage.refreshPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        footerPage.newsletterInput("test@gmail.com");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterSuccessText(),"This email is already subscribed!",
                "The system not checks if the user already subscribed to newsletter");
        softAssert.assertAll();
    }
}
