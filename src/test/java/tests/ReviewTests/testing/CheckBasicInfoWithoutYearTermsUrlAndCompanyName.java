package tests.ReviewTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBasicInfoWithoutYearTermsUrlAndCompanyName extends BaseTest {

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
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check Bookmaker content")
    public void checkBookmakerBasicInfoWithoutOptionalFields() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnNavBarItemNew("Basic Info");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Basic Info"), """
                Overview-test
                Basic Info
                Company Name
                company-name-test
                Year Established
                2020
                Jurisdiction
                Algeria, Afghanistan
                Website Terms & Conditions
                https://github.com/
                Products
                Esports, Sports, Live Casino, Pool Betting
                SHOW LESS""", "Error on the first Assertion: large");
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_WITHOUT_COMPANY_TERMSURL_YEAR);
        mainPage.refreshPage();
        reviewPage.clickOnNavBarItemNew("Basic Info");
        softAssert.assertEquals(reviewPage.getContentBySectionName("Basic Info"), """
                Overview-test
                Basic Info
                Year Established
                2020
                Jurisdiction
                Algeria, Afghanistan
                Products
                Esports, Sports, Live Casino, Pool Betting
                SHOW LESS""", "Error on the second Assertion: brief");
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
