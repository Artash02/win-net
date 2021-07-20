package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class CheckBookmakerSortingByRatings extends BaseTest {

    @BeforeMethod(description = " five more bookmakers")
    public void createBookmaker() throws Exception {

        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_5};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_1
                , BookmakerApiCalls.bookmaker_Name_1);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_2
                , BookmakerApiCalls.bookmaker_Name_2);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_3
                , BookmakerApiCalls.bookmaker_Name_3);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_4
                , BookmakerApiCalls.bookmaker_Name_4);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_5
                , BookmakerApiCalls.bookmaker_Name_5);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_5,"published");
    }

    @Test(description = "WN-296 Checks bookmakers sorting according to ratings")
    public void checkBookmakerSortingByRatings() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        filterPage.selectCountryInHeader("All Countries");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Rankings"),
                "Filter by 'Rankings' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Bonus Amount");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Bonus Amount"),
                "Filter by 'Bonus Amount' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Sport / Game Coverage");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Sport / Game Coverage"),
                "Filter by 'Sport / Game Coverage' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Responsible Gaming");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Responsible Gaming"),
                "Filter by 'Responsible Gaming' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Deposit & Withdrawal");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Deposit & Withdrawal"),
                "Filter by 'Deposit & Withdrawal' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Onboarding Process");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Onboarding Process"),
                "Filter by 'Onboarding Process' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Customer Service");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Customer Service"),
                "Filter by 'Customer Service' is not correct: see details in log;");

        bookmakersTablePage.selectValueInSortByDropDown("Utility Features");
        softAssert.assertTrue(bookmakersTablePage.checkBookmakerSortingByCriteriaName("Utility Features"),
                "Filter by 'Utility Features' is not correct: see details in log;");
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

