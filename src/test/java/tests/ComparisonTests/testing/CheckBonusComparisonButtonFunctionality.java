package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBonusComparisonButtonFunctionality extends BaseTest {

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

    @Test(description = "WN-114 Bookmakers full review checking with changed country and checking list and chart after that. ")
    public void checkBonusComparisonButtonFunctionality() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");

        Assert.assertTrue(mainPage.urlContains("countries=__all__"), "countries=__all__");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries", "'All Countries' was not selected!!!");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
        softAssert.assertTrue(mainPage.waitToTitleContains("@aaeeeee Winners.net")
                , "'@aaeeeee Winners.net' expression - not found in header text.");

        //comparisonPage.clickOnBonusesButton();
        reviewPage.clickOnNavBarItem("Bonuses");

        softAssert.assertTrue(reviewPage.bookmakerNameInHeader().contains(BookmakerApiCalls.bookmakerName)
                , "'" + BookmakerApiCalls.bookmakerName + "' name was not displayed in header!!!");
        softAssert.assertTrue(reviewPage.compareWithOthersInHeader().contains("COMPARE WITH OTHERS")
                , "'COMPARE WITH OTHERS' name was not displayed in header!!!");
        softAssert.assertTrue(reviewPage.websideInHeader().contains("WEBSITE")
                , "'WEBSITE' name was not displayed in header!!!");

        reviewPage.clickOnBonusComparisonButton();
//TODO
//        softAssert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
//                ,"'All Countries' was not displayed after 'Bonus Comparison' button click!!!");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1)
                .contains("blue " + BookmakerApiCalls.bookmakerName), "' was not displayed in Chart-legend!!!");
        softAssert.assertTrue(bookmakersTablePage.valueInSortByDropDown().equals("Bonus Amount")
                , "Wrong Filter was set!!!");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}