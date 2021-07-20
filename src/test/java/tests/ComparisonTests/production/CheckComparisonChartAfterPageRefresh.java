package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;


public class CheckComparisonChartAfterPageRefresh extends BaseTest {

    @Test(description = "WN-133 Bookmaker Comparison-Refresh/Back page")
    public void checkComparisonChartAfterPageRefreshProd() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.numberOfChartsInLegend
                (comparisonPage.addNBookmakersFromNbr(2,1,"ascending")));

        mainPage.refreshPage();
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        Assert.assertTrue(mainPage.urlContains("/review/bet365"));

        mainPage.goToBackPage();
        comparisonPage.chooseComparisonSubTabByName("Comparison");

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage.numberOfCheckedBookmakers
                (bookmakersTablePage.getListOfCheckedBookmakers()));

        Assert.assertEquals(lstBeforeSort,lstAfterSort
                ,"Numbers of Charts in Legends before and after sort DO NOT MATCH!!!");

    }
}
