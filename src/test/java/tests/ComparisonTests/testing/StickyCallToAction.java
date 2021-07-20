package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class StickyCallToAction extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "WAF-7 Full review scroll down functionality checking for bookmaker.")
    public void checkStickyCallToActionProd() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Logo");
        Assert.assertTrue(mainPage.waitToTitleContains("@aaeeeee Winners.net"),
                "'"+BookmakerApiCalls.bookmakerName+" was not found in Title.");
        Assert.assertEquals(mainPage.getCurrentUrl(), baseUrl+"/review/"
                +BookmakerApiCalls.bookmakerName,"Page URL does not contain "+baseUrl+"/review/"+BookmakerApiCalls.bookmakerName);

        reviewPage.clickOnCompareWithOthersButton();

        softAssert.assertNotNull(comparisonPage.getNamesAndColorsFromLegend(1),"Charts should not be empty.");

        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Website");
        comparisonPage.switchToNextTab();
        Assert.assertTrue(mainPage.waitToTitleContains("GitHub: Where the world builds software · GitHub"),
                "'GitHub: Where the world builds software · GitHub' was not found in Title.");
        Assert.assertEquals(webDriver().getCurrentUrl(), "https://github.com/","Wrong Page- displayed.");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}