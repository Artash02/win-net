package tests.CleanURLsTests;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckNavigateBack extends BaseTest {
    @BeforeMethod(description = "1. Create bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId,
                bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "WN-246 : (OK) Navigate-back")
    public void checkNavigateBackCleanURL () {
        mainPage.refreshPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        bookmakersTablePage.clickOnBookmakerReview(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(bookmakersPage.getOverviewTitleText(), BookmakerApiCalls.bookmakerName + " Review",
                "The bookmaker overview text is wrong");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() + "/",
                "homepage URL is wrong");
        softAssert.assertEquals(filterPage.countryIs(), "Armenia");
        filterPage.selectCountryInHeader("Algeria");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        softAssert.assertEquals(filterPage.countryIs(), "Algeria",
                "The current country is not Algeria");
        mainPage.goToBackPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(filterPage.countryIs(), "Algeria",
                "The current country is not Algeria after going back");
        softAssert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() + "/?countries=" +
                bookmakerApiCalls.getCountryIdByName("Algeria") +
                "&sort=-reviews.overall.rating", "The current URL is wrong for Algeria country after going back");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
