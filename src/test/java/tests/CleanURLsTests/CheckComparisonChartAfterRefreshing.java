package tests.CleanURLsTests;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckComparisonChartAfterRefreshing  extends BaseTest {
    @BeforeMethod(description = "1. Create 2 bookmakers")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_1,
                bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1,
                bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1,
                bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerPublishStatus(BookmakerApiCalls.bookmaker_Id_1, "published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_2,
                bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2,
                bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2,
                bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerPublishStatus(BookmakerApiCalls.bookmaker_Id_2, "published");
    }

    @Test(description = "WN-247 : (OK) Comparison chart after refreshing")
    public void comparisonChartKeptAfterRefresh() throws InterruptedException {
        mainPage.refreshPage();
        comparisonPage.chooseComparisonSubTabByName("Comparison");
        mainPage.waitToProgressBarIsNotDisplayed();
        mainPage.addBookmakerToComparisonByPosition(1);
        mainPage.addBookmakerToComparisonByPosition(2);
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2,
                "The bookmakers count added into the comparison chart on comparison tab is incorrect");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToProgressBarIsNotDisplayed();
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2,
                "The bookmakers count added into the comparison chart on Comparison page is incorrect");
        mainPage.refreshPage();
        //after refresh the comparison chart erases and it performs a bug
        softAssert.assertEquals(mainPage.getComparisonChartBookmakersCount(),2,
                "The bookmakers count added into the comparison chart after refresh is incorrect");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
    }
}
