package tests.BookmakersTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import org.testng.annotations.BeforeMethod;

public class CheckBookmakerRatingColor extends BaseTest {

    @BeforeMethod(description = "Create Bookmakers")
    public void publishBookmakers() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
        ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_1, BookmakerApiCalls.bookmaker_Name_1);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_2, BookmakerApiCalls.bookmaker_Name_2);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_3, BookmakerApiCalls.bookmaker_Name_3);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_4, BookmakerApiCalls.bookmaker_Name_4);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");
    }

    @Test(description = "Check bookmaker rating colors")
    public void checkBookmakersRatingColors() throws InterruptedException {
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(bookmakersTablePage.getScoreColorByBookmakerName(BookmakerApiCalls.bookmaker_Name_1), "Strong green");
        Assert.assertEquals(bookmakersTablePage.getScoreColorByBookmakerName(BookmakerApiCalls.bookmaker_Name_2), "Citrus");
        Assert.assertEquals(bookmakersTablePage.getScoreColorByBookmakerName(BookmakerApiCalls.bookmaker_Name_3), "Sunglow");
        Assert.assertEquals(bookmakersTablePage.getScoreColorByBookmakerName(BookmakerApiCalls.bookmaker_Name_4), "Radical Red");
    }

    @AfterMethod(alwaysRun = true,description = "Delete created bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
    }
}
