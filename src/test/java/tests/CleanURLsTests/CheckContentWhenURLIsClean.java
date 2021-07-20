package tests.CleanURLsTests;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckContentWhenURLIsClean extends BaseTest {
    @BeforeMethod(description = "1. Create bookmaker that is restricted for Armenia")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId,
                bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmakerId,"published", 1);
    }

    @Test(description = "WN-243 : (NOK) Check content when URL is clean")
    public void checkBookmakerNotShownWhenRestricted() {
        mainPage.refreshPage();
        filterPage.searchBookmakerName(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set",
                "There are bookmakers on bookmakers list");
        footerPage.clickOnReviewsSubMenuElement(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(bookmakersPage.getOverviewTitleText(), BookmakerApiCalls.bookmakerName + " Review",
                "The bookmaker page overview text is incorrect");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertTrue(filterPage.getChartElement(), "The current tab is not comparison");
        softAssert.assertEquals(filterPage.countryIs(), "Armenia", "The country is not Armenia");
        filterPage.searchBookmakerName(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set",
                "There are bookmakers on bookmakers list for search for Armenia");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
