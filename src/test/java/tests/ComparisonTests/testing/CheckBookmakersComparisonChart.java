package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakersComparisonChart extends BaseTest  {

    @BeforeMethod(description = "WAF-12 Create one and five more bookmakers")
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

        String[] namesOfBookmakers= {BookmakerApiCalls.bookmaker_Name_1,BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4,BookmakerApiCalls.bookmaker_Name_5};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_1
                ,BookmakerApiCalls.bookmaker_Name_1);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_2
                ,BookmakerApiCalls.bookmaker_Name_2);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_3
                ,BookmakerApiCalls.bookmaker_Name_3);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_4
                ,BookmakerApiCalls.bookmaker_Name_4);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_4,"published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addReviewsForSorting(BookmakerApiCalls.bookmaker_Id_5
                ,BookmakerApiCalls.bookmaker_Name_5);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_5,"published");
     }

    @Test(description = "WAF-12 Comparison chart checking.")
    public void checkBookmakersComparisonChart() throws Exception {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

//  Adding  the first 5 bookmakers one by one. Checking each time that the chart color for the 1st bookmaker with highest overall rating - was changed correspondingly.

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_1,"Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("blue "
                +BookmakerApiCalls.bookmaker_Name_1),BookmakerApiCalls.bookmaker_Name_1+" does not have Blue color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2,"Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(2).contains("green "
                +BookmakerApiCalls.bookmaker_Name_1),BookmakerApiCalls.bookmaker_Name_1+" does not have Green color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_3,"Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(3).contains("red "
                +BookmakerApiCalls.bookmaker_Name_1) ,BookmakerApiCalls.bookmaker_Name_1+" does not have Red color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_4,"Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(4).contains("purple "
                +BookmakerApiCalls.bookmaker_Name_1), BookmakerApiCalls.bookmaker_Name_1+" does not have Purple color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_5,"Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(5).contains("orange "
                +BookmakerApiCalls.bookmaker_Name_1),BookmakerApiCalls.bookmaker_Name_1+" does not have Orange color");

//  Checking if the 6th bookmaker can be checked, it should not. Verifying that it was not checked

        Integer initialNumberOfDisabledBookmakers=bookmakersTablePage.getNumberOfDisabledBookmakers();
        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName,"Plus");
        Integer newNumberOfDisabledBookmakers=bookmakersTablePage.getNumberOfDisabledBookmakers();
        softAssert.assertTrue(initialNumberOfDisabledBookmakers.equals(newNumberOfDisabledBookmakers)
                ,"'Plus' button for newly added bookmaker:"+BookmakerApiCalls.bookmakerName+" is disabled, it should not be disabled.");

//  unckecking the 2nd bookmaker and verifying that its graph is missing in the chart

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmaker_Name_2,"Plus");
        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(4).contains(
                BookmakerApiCalls.bookmaker_Name_2),"Bookmaker: "+BookmakerApiCalls.bookmaker_Name_2+
                " is displayed in chart. It should not be displayed.");

//  the 6th in the list bookmaker is checked instead of the 2nd - disabled one. Verifying its graph is in the chart

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName,"Plus");
        softAssert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains(BookmakerApiCalls.bookmakerName),
                "Newly checked bookmaker: "+BookmakerApiCalls.bookmakerName+" is not displayed in chart. It should be displayed.");

        softAssert.assertAll();
    }
    @AfterMethod(alwaysRun = true,description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}