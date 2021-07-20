package tests.FooterTests;


import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckNewsLetterFunctionality extends BaseTest {
    @Test(description = "WN-79 : (OK) News Letter functionality")
    public void checkNewsletter() {
        softAssert.assertEquals(footerPage.getNewsletterHeaderText(), "Newsletter");
        softAssert.assertEquals(footerPage.getNewsletterDescriptionText(), "Enter your email address and receive " +
                "updates for the best available offers and bookmakers");
        footerPage.clickOnIamInterestedButton();
        footerPage.newsletterInput("test@gmail.com");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterSuccessText(), "Subscription successful!",
                "Validation message is wrong for success case"); //This test is failure because of testing environment issue
        footerPage.newsletterInput("testetes");
        footerPage.clickOnIamInterestedButton();
        softAssert.assertEquals(footerPage.getNewsletterErrorText(), "Email is Invalid",
                "Validation message is wrong for negative case");
        softAssert.assertAll();
    }
}
