package tests.ReviewTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerBasicInfoSectionContent extends BaseTest {

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
        bookmakerApiCalls.addBookmakerData("seo", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_SEO_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "Check Bookmaker content")
    public void checkBookmakerBasicInfoContent() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        mainPage.waitToProgressBarIsNotDisplayed();
        reviewPage.clickOnReadMoreButton();
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Company Name","Year Established"), "Company-Name-Test");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Year Established","Jurisdiction"), "2021");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction","Website Terms & Conditions"), "Afghanistan, Austria, Bangladesh");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Jurisdiction", "Website Terms & Conditions"), "https://www.bookmakers.bet/");
        softAssert.assertEquals(reviewPage.getReviewContentBetweenTitles("Products",null),"Esports, Sports, Live Casino, Pool Betting");
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
