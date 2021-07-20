package tests.ComparisonTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class CompareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings extends BaseTest {

    @BeforeMethod(description = "Create one bookmaker")
    public void createBookmaker() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "WN-297 Retrieves coordinates from comparison chart and compares with ones created on ratings from API")
    public void compareDomChartCoordinatesWithCoordinatesCreatedOverAPIRatings() throws InterruptedException {

        mainPage.selectTabInHeader("Bookmaker Comparison");
        bookmakersTablePage.clickOnShowAllButtonIfDisplayed();

        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Plus");
        System.out.println("\n" + BookmakerApiCalls.bookmakerName);
        System.out.println("Bookmaker ID: " + BookmakerApiCalls.bookmakerId + "\n");

        Assert.assertEquals(comparisonPage.getOverallRatingFromApi(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)),
                comparisonPage.calculateOverallRatingOnDomCoordinates(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId)),
                "API Overall Rating and Overall Rating Calculated on DOM Coordinates - DO NOT MATCH!!!");

        Assert.assertEquals(comparisonPage.createCoordinatesOnApiRatings(comparisonApiCalls.getRatingsFromApi(BookmakerApiCalls.bookmakerId))
                , comparisonPage.getComparisonChartCoordinates());
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
