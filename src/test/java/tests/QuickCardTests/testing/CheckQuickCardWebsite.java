package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckQuickCardWebsite extends BaseTest {
    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image 4. Add Data in Bookmaker Profile section" +
            "5. Add Bonus for bookmaker, 6. Add data in 'Reviews' tab, 7. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test",1, BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "WN-97 (WAF-282 & WAF-281) Check quick card review functionality")
    public void checkQuickCardReviewFunctionality() {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        quickCardPage.clickOnQuickCardButton(BookmakerApiCalls.bookmakerName, "website");
        mainPage.handleWindowByTitle("GitHub: Where the world builds software · GitHub");
        Assert.assertEquals(mainPage.getPageUrl(), "https://github.com/");
    }

    @AfterMethod(alwaysRun = true,description = "delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }

}
