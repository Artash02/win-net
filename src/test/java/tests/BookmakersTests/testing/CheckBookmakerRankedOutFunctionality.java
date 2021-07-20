package tests.BookmakersTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakerRankedOutFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add  six digits in bookmaker bonus value field")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_OVERVIEW_SPACE);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check Bookmaker Ranked out functionality")
    public void checkBookmakerRankedFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(mainPage.urlContains("bookmaker-comparison"));
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        Assert.assertTrue(mainPage.urlContains(BookmakerApiCalls.bookmakerName));
        Assert.assertEquals(reviewPage.getRankedOut()
                , "#" + bookmakerApiCalls.getBookmakersCount() + " Ranked Out Of "
                        + bookmakerApiCalls.getBookmakersCount() + " Reviewed Sites\n6.7 / 10");
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
