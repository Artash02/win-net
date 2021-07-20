package tests.ReviewTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerInfoEditFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bonus for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerData("seo", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_SEO_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check Bookmaker content")
    public void checkBookmakerInfoEditFunctionality() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnReadMoreButton();
//        softAssert.assertEquals(reviewPage.getOverviewContent(), "Main Review-overview");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Company Name", "Year Established"), "company-name-test");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Year Established", "Jurisdiction"), "2020");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction", "Website Terms & Conditions"), "Algeria, Afghanistan");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Website Terms & Conditions", "Products"), "https://github.com/");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Products", null), "Esports, Sports, Live Casino, Pool Betting");
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.UPDATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.UPDATE_BOOKMAKER_REVIEW_TAB);
        mainPage.refreshPage();
        reviewPage.clickOnReadMoreButton();
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Company Name", "Year Established"), "Company-Name-Test-edit");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Year Established", "Jurisdiction"), "2020");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction", "Website Terms & Conditions"), "Afghanistan");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Website Terms & Conditions", "Products"), "https://www.vivarobet.am/");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Products", null), "Esports, Sports, Pool Betting");
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
