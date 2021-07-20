package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;


public class CheckComparisonChartAfterPageRefresh extends BaseTest {

    @BeforeMethod(description = "Create 2 bookmakers")
    public void createBookmakers() throws Exception {
        String[] namesOfBookmakers= {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");
    }

    @Test(description = "WN-133 Bookmaker Comparison-Refresh/Back page")
    public void checkComparisonChartAfterPageRefresh() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.numberOfChartsInLegend
                (comparisonPage.addNBookmakersFromNbr(2,1,"ascending")));

        bookmakersTablePage.refreshPage();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2, "Full Review");
        //Thread.sleep(500);
        Assert.assertTrue(mainPage.urlContains("/review/"+BookmakerApiCalls.bookmaker_Name_2));
        mainPage.goToBackPage();

        comparisonPage.chooseComparisonSubTabByName("Comparison");

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage.numberOfCheckedBookmakers
                (bookmakersTablePage.getListOfCheckedBookmakers()));

        Assert.assertEquals(lstAfterSort,lstBeforeSort
                ,"Numbers of Charts in Legends before and after sort DO NOT MATCH!!!");

    }

    @AfterMethod(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
    }
}