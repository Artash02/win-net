package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class CheckQuickCardSortedByRankings extends BaseTest {

    @BeforeMethod(description = "1. Create 5 bookmakers")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_5};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-1", 1, BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-2", 2, BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-3", 3, BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-4", 4, BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-5", 5, BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_5,"published");

    }

    @Test()
    public void test() throws InterruptedException {
         mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo( 1),
                BookmakerApiCalls.bookmaker_Name_1+", 6.7 / 10, Text-to-display-test-1, REVIEW, WEBSITE");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo( 2),
                BookmakerApiCalls.bookmaker_Name_2+", 6.7 / 10, Text-to-display-test-2, REVIEW, WEBSITE");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(3),
                BookmakerApiCalls.bookmaker_Name_3+", 6.7 / 10, Text-to-display-test-3, REVIEW, WEBSITE");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(4),
                BookmakerApiCalls.bookmaker_Name_4+", 6.7 / 10, Text-to-display-test-4, REVIEW, WEBSITE");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(5),
                BookmakerApiCalls.bookmaker_Name_5+", 6.7 / 10, Text-to-display-test-5, REVIEW, WEBSITE");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true,description = "delete created bookmaker")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
    }
}
