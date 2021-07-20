package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckComparisonChartAvailabilityForDifferentBookmakers extends BaseTest {

    @BeforeMethod(description = "Create 3 bookmakers")
    public void createBookmakers() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1,
                BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_3};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");
    }

    @Test(description = "WN-132 Checks comparison chart ability when bookmaker deleting in bookmakers list.")
    public void checkComparisonChartAvailabilityForDifferentBookmakers() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                ,"'All Countries' was not selected!!!");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), comparisonApiCalls
                .getTotalNumberOfBookmakersFromApi(), "Numbers of bookmakers Do NOT MATCH");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(), comparisonApiCalls.getTotalNumberOfBookmakersFromApi(),
                "Numbers of bookmakers Do NOT MATCH after Comparison button click");

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_1, "Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2, "Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_3, "Plus");

        filterPage.selectCountryInHeader("Armenia");

        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(1).contains(BookmakerApiCalls.bookmaker_Name_1)
                , "Bookmaker should not be in the list!!!");
        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(1).contains(BookmakerApiCalls.bookmaker_Name_2)
                , "Bookmaker should not be in the list!!!");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("blue "
                        + BookmakerApiCalls.bookmaker_Name_3)
                , "Wrong chart is Displayed!!!");//rgb(9, 105, 250)GG.BET: GG.BET in blue (250)

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
    }
}
