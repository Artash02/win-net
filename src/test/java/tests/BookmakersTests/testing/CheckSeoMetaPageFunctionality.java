package tests.BookmakersTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckSeoMetaPageFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Best Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerData("seo", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_SEO_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "Check Seo meta page functionality")
    public void checkSeoMetaPageFunctionality() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(mainPage.urlContains("bookmaker-comparison"));
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
        Assert.assertTrue(mainPage.urlContains(BookmakerApiCalls.bookmakerName));
        softAssert.assertEquals(mainPage.getTitle(), "Meta-title-test");
        bookmakerApiCalls.addBookmakerData("seo", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.UPDATE_BOOKMAKER_SEO_TAB);
        mainPage.refreshPage();
        softAssert.assertEquals(mainPage.getTitle(),"Meta-title-test-edit");
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
