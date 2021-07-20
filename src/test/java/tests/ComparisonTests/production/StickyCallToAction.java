package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class StickyCallToAction extends BaseTest {

    @Test(description = "WAF-7 Full review scroll down functionality checking for bookmaker.")
    public void checkStickyCallToActionProd() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Logo");
        //need update waitToTitleContains
        Assert.assertTrue(mainPage.waitToTitleContains("Betway Review"),
                "'Betway Review' was not found in Title.");
        Assert.assertEquals(mainPage.getCurrentUrl(), baseUrl+"/review/betway","Page URL does not contain "+baseUrl+"/review/betway.");

        reviewPage.clickOnCompareWithOthersButton();

        softAssert.assertNotNull(comparisonPage.getNamesAndColorsFromLegend(1),"Charts should not be empty.");

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway", "Website");
        comparisonPage.switchToNextTab();
        //need update waitToTitleContains
        Assert.assertTrue(mainPage.waitToTitleContains("Betway CA Sports offer"),"'Betway CA Sports offer' was not found in Title.");
        softAssert.assertEquals(mainPage.getCurrentUrl(), "https://betway.com/bwp/sportsoffercan/en-ca/"
                ,"Wrong Page- displayed.");
        softAssert.assertAll();
    }
}