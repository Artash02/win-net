package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;

public class CheckComparisonChartColorChanging extends BaseTest {

    @Test(description = "WN-107 Comparison-Color-Changing (High overall rating chart changes color")
    public void checkComparisonChartColorChangingProd() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");

        ArrayList directAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.numberOfChartsInLegend
                (comparisonPage.addNBookmakersFromNbr(2,1,"ascending")));

        comparisonPage.addNBookmakersFromNbr(2, 1,"ascending");//removes already checked bookmakers

        ArrayList reverseAdd=comparisonPage.getNamesAndColorsFromLegend(comparisonPage.numberOfChartsInLegend
                (comparisonPage.addNBookmakersFromNbr(2,2,"descending")));
        Assert.assertEquals(directAdd,reverseAdd, "Color changing did not happen.");
    }
}