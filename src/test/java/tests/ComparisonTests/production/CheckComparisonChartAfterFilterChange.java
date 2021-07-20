package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;


public class CheckComparisonChartAfterFilterChange extends BaseTest {

    @Test(description = "WN-150 Comparison-Chart after changing filter")
    public void checkComparisonChartAfterFilterChange() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        ArrayList lstBeforeSort=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.numberOfChartsInLegend
                (comparisonPage.addNBookmakersFromNbr(4,1,"ascending")));

        bookmakersTablePage.selectValueInSortByDropDown("Sport / Game Coverage");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        ArrayList lstAfterSort=comparisonPage.getNamesAndColorsFromLegend(bookmakersTablePage.numberOfCheckedBookmakers
                (bookmakersTablePage.getListOfCheckedBookmakers()));

        Assert.assertEquals(lstBeforeSort,lstAfterSort,"Numbers of Charts in Legends before and after sort DO NOT MATCH!!!");
    }
}