package tests.CleanURLsTests;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckFiltersAreWorkingWhenURLIsClean extends BaseTest {
    @BeforeMethod(description = "1. Create bookmaker that is restricted for Armenia")
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

    @Test(description = "WN-249 : (OK) Filters are working when url is clean")
    public void checkFiltersWorkWhenURLIsClean() throws InterruptedException {
        mainPage.refreshPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        Assert.assertEquals(filterPage.countryIs(), "Armenia",
                "The country is displayed wrong");
        filterPage.searchBookmakerName(BookmakerApiCalls.bookmakerName);
        Thread.sleep(2000);
        softAssert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() +
                "/?countries=" + bookmakerApiCalls.getCountryIdByName("Armenia") + "&name=" +
                BookmakerApiCalls.bookmakerName + "&sort=-reviews.overall.rating",
                "The page URL is incorrect according to the search parameter");
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() + "/?countries=" +
                bookmakerApiCalls.getCountryIdByName("Armenia") + "?",
                "Shows incorrect URL on homepage for Armenia");
        filterPage.clickOnExpandIconsBySectionName("Other Products");
        filterPage.selectCheckbox("Other Products","Casino");
        filterPage.searchBookmakerName(BookmakerApiCalls.bookmakerName);
        softAssert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set",
                "There is bookmakers on bookmakers list");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
