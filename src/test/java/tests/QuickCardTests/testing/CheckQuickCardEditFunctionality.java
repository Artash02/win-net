package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckQuickCardEditFunctionality extends BaseTest {
    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image, 4. Add Quick card info, 5. Add Data in Bookmaker Profile section," +
            "6. Add Bonus for bookmaker, 7. Add data in 'Reviews' tab, 8. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test", 1, BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "WAF-256 Check quick card edit functionality")
    public void checkQuickCardReviewFunctionality() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");

        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmakerName + ", 6.7 / 10, Text-to-display-test, REVIEW, WEBSITE");
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-edit", 1, BookmakerApiCalls.bookmakerId);
        mainPage.refreshPage();
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmakerName + ", 6.7 / 10, Text-to-display-test-edit, REVIEW, WEBSITE");
        softAssert.assertEquals(quickCardPage.getQuickCardTags(BookmakerApiCalls.bookmakerName), new ArrayList<>(Arrays.asList("Valorant", "CS:GO")));
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}

