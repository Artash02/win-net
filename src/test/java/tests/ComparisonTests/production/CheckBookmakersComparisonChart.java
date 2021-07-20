package tests.ComparisonTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakersComparisonChart extends BaseTest {

    @Test(description = "WAF-12 Comparison chart checking.")

    public void checkBookmakersComparisonChart() throws Exception {

        mainPage.selectTabInHeader("Bookmaker Comparison");

//  Adding  the first 5 bookmakers one by one. Checking each time that the chart color for the 1st bookmaker with highest overall rating - was changed correspondingly.

        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(0), "Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(1).contains("blue " +
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)), bookmakersTablePage.getFirstNBookmakersInTable(5).get(0)
                + " does not have Blue color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(1), "Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(2).contains("green " +
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)), bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)
                + " does not have Green color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(2), "Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(3).contains("red " +
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)), bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)
                + " does not have Red color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(3), "Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(4).contains("purple " +
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)), bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)
                + " does not have Purple color");
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(4), "Plus");
        softAssert.assertTrue(comparisonPage.getNamesAndColorsFromLegend(5).contains("orange " +
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)), bookmakersTablePage.getFirstNBookmakersInTable(6).get(0)
                + " does not have Orange color");

//  Checking if the 6th bookmaker can be checked, it should not. Verifying that it was not checked

        Integer initialNumberOfDisabledBookmakers = bookmakersTablePage.getNumberOfDisabledBookmakers();
        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(5), "Plus");
        Integer newNumberOfDisabledBookmakers = bookmakersTablePage.getNumberOfDisabledBookmakers();
        softAssert.assertTrue(initialNumberOfDisabledBookmakers.equals(newNumberOfDisabledBookmakers)
                , "'Plus' button for newly added bookmaker: " + bookmakersTablePage.getFirstNBookmakersInTable(6).get(5)
                        + " is disabled, it should not be disabled.");

//  unckecking the 2nd bookmaker and verifying that its graph is missing in the chart

        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(1), "Plus");
        softAssert.assertFalse(comparisonPage.getNameFromLegendElement(4).contains(
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(1)), "Bookmaker: "
                + bookmakersTablePage.getFirstNBookmakersInTable(6).get(1) + " is displayed in chart. It should not be displayed.");

//  the 6th in the list bookmaker is checked instead of the 2nd - disabled one. Verifying its graph is in the chart

        bookmakersTablePage.clickOnButtonsByBookmakerName(bookmakersTablePage.getFirstNBookmakersInTable(6).get(5), "Plus");
        softAssert.assertTrue(comparisonPage.getNameFromLegendElement(5).contains(
                bookmakersTablePage.getFirstNBookmakersInTable(6).get(5)), "Newly checked bookmaker: "
                + bookmakersTablePage.getFirstNBookmakersInTable(6).get(5) + " is not displayed in chart. It should displayed.");

        softAssert.assertAll();
    }
}