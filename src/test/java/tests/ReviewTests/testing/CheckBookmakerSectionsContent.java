package tests.ReviewTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerSectionsContent extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "Check bookmaker 'Bonuses' section content")
    public void checkBookmakerSectionsContent() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnNavBarItem("Bonuses");
        Assert.assertEquals(reviewPage.getContentBySectionName("Bonuses"), "Bonuses\n" +
                "10 / 10\n" +
                "Bonus-info-introduction-test\n" +
                "Bonus-info-summary-test\n" +
                "Interested in what other bookmakers are offering?\n" +
                "BONUS COMPARISON", "'Bonuses' section content is not correct");
        reviewPage.clickOnNavBarItem("Sport / Game Coverage");
        Assert.assertEquals(reviewPage.getContentBySectionName("Sport / Game Coverage"), "Sport / Game Coverage\n" +
                "7 / 10\n" +
                "Sports-Games-Covered-Introduction\n" +
                "Esports\n" +
                "Valorant\n" +
                "CS:GO\n" +
                "Sports\n" +
                "Baseball\n" +
                "Valleyball\n" +
                "Basketball\n" +
                "Others\n" +
                "Live Casino\n" +
                "Pool Betting\n" +
                "Sports-Games-Covered-Summary","'Sport / Game Coverage' section content is not correct");
        reviewPage.clickOnNavBarItem("Responsible Gaming");
        Assert.assertEquals(reviewPage.getContentBySectionName("Responsible Gaming"), "Responsible Gaming\n" +
                "9 / 10\n" +
                "Responsible-Gaming-Introduction-test\n" +
                "Organizations\n" +
                "WHO\n" +
                "IBIS\n" +
                "Responsible-Gaming-Summary-test");
        reviewPage.clickOnNavBarItem("Deposit & Withdrawal");
        Assert.assertEquals(reviewPage.getContentBySectionName("Deposit & Withdrawal"), "Deposit & Withdrawal\n" +
                "1 / 10\n" +
                "Deposit-and-Withdrawal-test\n" +
                "Payment Options\n" +
                "SWIFT Transfers\n" +
                "Yandex Money\n" +
                "Withdrawal Options\n" +
                "Visa Electron\n" +
                "Maestro\n" +
                "Deposit-And-Withdrawal-test", "'Deposit & Withdrawal' section content is not correct");
        reviewPage.clickOnNavBarItem("Onboarding Process");
        Assert.assertEquals(reviewPage.getContentBySectionName("Onboarding Process"), "Onboarding Process\n" +
                "8 / 10\n" +
                "OnBoarding-process-intorduction-test\n" +
                "Account Verification\n" +
                "Account Verification Method\n" +
                "Email signature\n" +
                "Personal visita\n" +
                "OnBoarding-process-summary-test", "'Onboarding Process' section content is not correct");
        reviewPage.clickOnNavBarItem("Customer Service");
        Assert.assertEquals(reviewPage.getContentBySectionName("Customer Service"), "Customer Service\n" +
                "8 / 10\n" +
                "Customer-Services-introduction-test\n" +
                "Channels\n" +
                "Instant chat\n" +
                "Personal visit\n" +
                "Features\n" +
                "Professional treatment\n" +
                "Personalized service\n" +
                "Availability\n" +
                "500\n" +
                "Customer-Services-Summary-test", "'Customer Service' section content is not correct");
        reviewPage.clickOnNavBarItem("Utility Features");
        Assert.assertEquals(reviewPage.getContentBySectionName("Utility Features"), "Utility Features\n" +
                "4 / 10\n" +
                "Utility-Features-Introduction-test\n" +
                "Feature-1\n" +
                "Description-1\n" +
                "Utility-Features-Summary-test", "'Utility Features' section content is not correct");
        reviewPage.clickOnNavBarItem("Additional Info");
        Assert.assertEquals(reviewPage.getContentBySectionName("Additional Info"), "Additional Information\n" +
                "Excluded countries\n" +
                "Albania\n" +
                "Languages\n" +
                "Spanish\n" +
                "Currency accepted\n" +
                "RUB", "'Additional Info' section content is not correct");
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
