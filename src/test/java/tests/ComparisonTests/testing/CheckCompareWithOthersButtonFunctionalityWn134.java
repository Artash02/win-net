package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCompareWithOthersButtonFunctionalityWn134 extends BaseTest {

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

    @Test(description = "Check compare with others button functionality in Bookmaker review page")
    public void checkCompareWithOtherButtonFunctionality() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        reviewPage.clickOnCompareWithOthersButton();
        softAssert.assertTrue(mainPage.waitToTitleContains("Esports betting sites | Customize your need and find the best bookmaker"),
                "Title does not contain 'Esports betting sites | Customize your need and find the best bookmaker' expression");
        Assert.assertTrue(mainPage.getPageUrl().contains(baseUrl+"/bookmaker-comparison"),
                "Url does not contain "+baseUrl+"/bookmaker-comparison");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}