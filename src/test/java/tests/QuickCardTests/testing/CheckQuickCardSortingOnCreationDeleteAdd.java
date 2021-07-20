package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckQuickCardSortingOnCreationDeleteAdd extends BaseTest {

    @BeforeMethod(description = "1. Create 5 bookmakers")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_5};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-1", 1
                , BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-2", 2
                , BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-3", 3
                , BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_4);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-4", 4
                , BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_5);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-5", 5
                , BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_5,"published");
    }

    @Test(description = "WN-315 Check quick card in Din Review Pages:  QC-s with higher ranking (1,2...) should appear closer to the top of the list")
    public void checkQuickCardInDom() throws Exception {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToProgressBarIsNotDisplayed();

        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmaker_Name_1+", 6.7 / 10, Text-to-display-test-1, REVIEW, WEBSITE"
                ,"Error on QC Create: at 1 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(2),
                BookmakerApiCalls.bookmaker_Name_2+", 6.7 / 10, Text-to-display-test-2, REVIEW, WEBSITE"
                ,"Error on QC Create: at 2 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(3),
                BookmakerApiCalls.bookmaker_Name_3+", 6.7 / 10, Text-to-display-test-3, REVIEW, WEBSITE"
                ,"Error on QC Create: at 3 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(4),
                BookmakerApiCalls.bookmaker_Name_4+", 6.7 / 10, Text-to-display-test-4, REVIEW, WEBSITE"
                ,"Error on QC Create: at 4 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(5),
                BookmakerApiCalls.bookmaker_Name_5+", 6.7 / 10, Text-to-display-test-5, REVIEW, WEBSITE"
                ,"Error on QC Create: at 5 place");

        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"draft");

        mainPage.refreshPage();
        mainPage.waitToProgressBarIsNotDisplayed();

        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmaker_Name_2+", 6.7 / 10, Text-to-display-test-2, REVIEW, WEBSITE"
                ,"Error on QC Delete1: at 1 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(2),
                BookmakerApiCalls.bookmaker_Name_3+", 6.7 / 10, Text-to-display-test-3, REVIEW, WEBSITE"
                ,"Error on QC Delete1: at 2 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(3),
                BookmakerApiCalls.bookmaker_Name_4+", 6.7 / 10, Text-to-display-test-4, REVIEW, WEBSITE"
                ,"Error on QC Delete1: at 3 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(4),
                BookmakerApiCalls.bookmaker_Name_5+", 6.7 / 10, Text-to-display-test-5, REVIEW, WEBSITE"
                ,"Error on QC Delete1: at 4 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(5),
                null,"Error on QC Delete1: 5 place");

        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"draft");
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"draft");
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"draft");

        mainPage.refreshPage();
        mainPage.waitToProgressBarIsNotDisplayed();

        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmaker_Name_5+", 6.7 / 10, Text-to-display-test-5, REVIEW, WEBSITE"
                ,"Error on QC Delete2: at 1 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(2),
                null,"Error on QC Delete2: at 2 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(3),
                null,"Error on QC Delete2: at 3 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(4),
                null,"Error on QC Delete2: at 4 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(5),
                null,"Error on QC Delete2: at 5 place");

        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");

        mainPage.refreshPage();
        mainPage.waitToProgressBarIsNotDisplayed();

        softAssert.assertEquals(quickCardPage.getQuickCardInfo(1),
                BookmakerApiCalls.bookmaker_Name_1+", 6.7 / 10, Text-to-display-test-1, REVIEW, WEBSITE"
                ,"Error on QC Add: at 1 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(2),
                BookmakerApiCalls.bookmaker_Name_2+", 6.7 / 10, Text-to-display-test-2, REVIEW, WEBSITE"
                ,"Error on QC Add: at 2 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(3),
                BookmakerApiCalls.bookmaker_Name_3+", 6.7 / 10, Text-to-display-test-3, REVIEW, WEBSITE"
                ,"Error on QC Add: at 3 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(4),
                BookmakerApiCalls.bookmaker_Name_4+", 6.7 / 10, Text-to-display-test-4, REVIEW, WEBSITE"
                ,"Error on QC Add: at 4 place");
        softAssert.assertEquals(quickCardPage.getQuickCardInfo(5),
                BookmakerApiCalls.bookmaker_Name_5+", 6.7 / 10, Text-to-display-test-5, REVIEW, WEBSITE"
                ,"Error on QC Add: at 5 place");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
    }
}

