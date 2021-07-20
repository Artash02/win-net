package tests.BookmakersTests;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckShowMoreBookmakersButtonBehaviourInDifferentPages extends BaseTest {

    @BeforeMethod(description = "Create over 10 published bookmakers either by adding or by changing statuses")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");

        String[] namesOfBookmakers = {
                BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4,
                BookmakerApiCalls.bookmaker_Name_5, BookmakerApiCalls.bookmaker_Name_6,
                BookmakerApiCalls.bookmaker_Name_7, BookmakerApiCalls.bookmaker_Name_8,
                BookmakerApiCalls.bookmaker_Name_9, BookmakerApiCalls.bookmaker_Name_10
        };
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_5, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_6);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_6
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_6
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_6,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_6, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_7);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_7
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_7
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_7,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_7, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_8);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_8
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_8
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_8,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_8, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_9);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_9
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_9
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_9,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_9, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_10);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_10
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_10
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_10,
                bookmakerApiCalls.CREATE_BOOKMAKER_HIGH_SPORTS_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_10, "published");
    }

    @Test(description = "WN-313 : Checking 'Show More Bookmakers' button behaviour in different pages(WAF-499)")
    public void checkShowMoreBookmakersButtonBehaviourInDifferentPages() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");

        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at Homepage");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at Homepage.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount()
                , comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at Homepage.");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at 'Bookmaker Comparison'!");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at 'Bookmaker Comparison'.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount()
                , comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at 'Bookmaker Comparison'.");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(mainPage.urlContains("bookmaker-comparison"), "Url not contains 'bookmaker-comparison' value");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_1, "Plus");
        mainPage.refreshPage();
        Assert.assertEquals(bookmakersTablePage.getButtonName(), "SHOW FEWER BOOKMAKERS");

        mainPage.selectTabInHeader("Best Sports Betting Sites");
        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at 'Best Sports Betting Sites'");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at 'Best Sports Betting Sites'.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount()
                , comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at 'Best Sports Betting Sites'.");

        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at 'Best CS:GO Betting Sites'!");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at 'Best CS:GO Betting Sites'.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(),
                comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at 'Best CS:GO Betting Sites'.");

        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at at 'Best Dota2 Betting Sites'!");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at 'Best Dota2 Betting Sites'.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount()
                , comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at 'Best Dota2 Betting Sites'.");

        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        Assert.assertTrue(bookmakersTablePage.isShowAllBookmakersButtonVisible()
                , "'Show All Bookmakers' button is not present at 'Best LoL Betting Sites'!");
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW MORE BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount(), 10
                , "Wrong number of bookmakers is listed at 'Best LoL Betting Sites'.");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Assert.assertEquals(bookmakersTablePage.getShowAllBookmakersButtonCurrentText()
                , "SHOW FEWER BOOKMAKERS", "Wrong Caption is displayed");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCurrentCount()
                , comparisonApiCalls.getTotalNumberOfBookmakersFromApi()
                , "Wrong number of bookmakers is listed at 'Best LoL Betting Sites'.");
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_6);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_7);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_8);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_9);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_10);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
