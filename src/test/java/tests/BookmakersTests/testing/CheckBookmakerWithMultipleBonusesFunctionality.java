package tests.BookmakersTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakerWithMultipleBonusesFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add multiple bonuses for bookmaker, 5. Add data in 'Reviews' tab, 6. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_MULTIPLE_BONUSES);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerData("seo", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_SEO_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check bookmaker with multiple bonuses functionality")
    public void checkBookmakerWithMultipleBonusesFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        Assert.assertTrue(mainPage.urlContains(BookmakerApiCalls.bookmakerName));
        Assert.assertEquals(reviewPage.getContentBySectionName("Bonuses"), "Bonuses\n" +
                "6 / 10\n" +
                "introduction-test\n" +
                "Bonus1-Armenia\n" +
                "bonus1-country-Armenia-test\n" +
                "Bonus2-country-is-all-countries\n" +
                "Summary-select-all-test\n" +
                "summary-test\n" +
                "Interested in what other bookmakers are offering?\n" +
                "BONUS COMPARISON");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        filterPage.selectCountryInHeader("Australia");
        mainPage.refreshPage();
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmakerName);
        Assert.assertTrue(mainPage.urlContains(BookmakerApiCalls.bookmakerName));
        Assert.assertEquals(reviewPage.getContentBySectionName("Bonuses"), "Bonuses\n" +
                "6 / 10\n" +
                "introduction-test\n" +
                "Bonus1-Armenia\n" +
                "bonus1-country-Armenia-test\n" +
                "Bonus2-country-is-all-countries\n" +
                "Summary-select-all-test\n" +
                "summary-test\n" +
                "Interested in what other bookmakers are offering?\n" +
                "BONUS COMPARISON");
    }

    @AfterMethod(description = "Delete created Article")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }

}
