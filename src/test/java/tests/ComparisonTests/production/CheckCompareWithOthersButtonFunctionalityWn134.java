package tests.ComparisonTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckCompareWithOthersButtonFunctionalityWn134 extends BaseTest {

    @Test(description = "Check compare with others button functionality in Bookmaker review page")
    public void checkCompareWithOtherButtonFunctionality() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Full Review");
        //need to be updated waitToTitleContains
        softAssert.assertTrue(mainPage.waitToTitleContains("Betway Review"), "Title does not contain 'Betway Review' expression");
        reviewPage.clickOnCompareWithOthersButton();
        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertTrue(mainPage.getPageUrl().contains(baseUrl+"/bookmaker-comparison"),
                "Url does not contain "+baseUrl+"/bookmaker-comparison");
        softAssert.assertAll();
    }
}