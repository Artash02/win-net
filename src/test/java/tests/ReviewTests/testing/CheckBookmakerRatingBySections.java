package tests.ReviewTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings("AccessStaticViaInstance")
public class CheckBookmakerRatingBySections extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check bookmaker rating by review section name")
    public void checkBookmakerRatingBySections() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Bonuses"), 10, "'Bonuses' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Sport / Game Coverage"), 7, "'Sport / Game COverage' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Responsible Gaming"), 9, "'Responsible Gaming' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Deposit & Withdrawal"), 1, "'Deposit & Withdrawal' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Onboarding Process"), 8, "'Onboarding Process' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Customer Service"), 8, "'Customer Service' section rating is not correct");
        softAssert.assertEquals(reviewPage.getRatingBySectionName("Utility Features"), 4, "'Utility Features' section rating is not correct");
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete Created bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
