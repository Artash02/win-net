package tests.ComparisonTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.List;

public class CompareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings extends BaseTest {

    @Test(description = "WN-297 Retrieves coordinates from comparison chart and compares with ones created on ratings from API")
    public void compareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Full Review");
        //need update waitToTitleContains
        mainPage.waitToTitleContains("Bet365 Review");

        List<Number> ratingsFromBookmakerPage=comparisonPage.getRatingsFromBookmakerPage();
        List<Number> ratingsFromBookmakerPage1=comparisonPage.getRatingsFromBookmakerPage();

        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        bookmakersTablePage.clickOnButtonsByBookmakerName("Bet365", "Plus");

        softAssert.assertEquals(String.valueOf(ratingsFromBookmakerPage.get(0)),
                String.valueOf(comparisonPage.calculateOverallRatingOnDomCoordinates(ratingsFromBookmakerPage)),
        "API Overall Rating and Overall Rating Calculated on DOM Coordinates - DO NOT MATCH!!!");

        softAssert.assertEquals(comparisonPage.createCoordinatesOnApiRatings(ratingsFromBookmakerPage1)
        ,comparisonPage.getComparisonChartCoordinates());
        softAssert.assertAll();
    }
}
