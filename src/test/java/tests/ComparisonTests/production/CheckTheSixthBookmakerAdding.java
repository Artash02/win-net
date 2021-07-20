package tests.ComparisonTests.production;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ComparisonPage;
import tests.base.BaseTest;


public class CheckTheSixthBookmakerAdding extends BaseTest  {

    @Test(description = "WN-163 Check Functionalities adding six bookmakers to comparison Chart")
    public void checkTheSixthBookmakerAdding() throws Exception {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToProgressBarIsNotDisplayed();

        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway","Plus");
        Assert.assertTrue(comparisonPage.getNameFromLegendElement(1).contains("Betway")
        , "Wrong chart!!!. Graphs do not contain 'Betway'.");

        comparisonPage.saveCurrentUrl();

        bookmakersTablePage.clickOnButtonsByBookmakerName("Betway","Plus");
        Assert.assertEquals("\"" +bookmakersTablePage.numberOfCheckedBookmakers(bookmakersTablePage.getListOfCheckedBookmakers())+"\"",
                "\"0\"", "Chart is not empty. It should be empty." );

        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("William Hill","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Unibet","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("1XBET","Plus");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Vbet","Plus");

        Assert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains ("Bet365")
                ,"Charts do not contain Bet365");
        Assert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains("William Hill")
                ,"Charts do not contain William Hill");
        Assert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains("Unibet")
                ,"Charts do not contain Unibet");
        Assert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains("1XBET")
                ,"Charts do not contain 1XBET");
        Assert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains("Vbet")
                ,"Charts do not contain Vbet");

        mainPage.openUrl(ComparisonPage.currentUrl);
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();
        Thread.sleep(2000);
        Assert.assertTrue( comparisonPage.getNameFromLegendElement(1).contains("Betway")
                ,"'Betway' was not selected after URL refresh.The same bookmaker should be selected after URL refresh");

    }
}