package tests.ComparisonTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakerSortingByRatings extends BaseTest {

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


}
