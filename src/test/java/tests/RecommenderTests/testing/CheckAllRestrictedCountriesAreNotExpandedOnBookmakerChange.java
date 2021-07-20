package tests.RecommenderTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckAllRestrictedCountriesAreNotExpandedOnBookmakerChange extends BaseTest {

    @BeforeMethod(description = "Create 2 bookmakers")
    public void createBookmakers() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_NINETEEN_COUNTRIES_BY_NAMES);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_NINETEEN_COUNTRIES_BY_NAMES);;
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("status", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.UPDATE_BOOKMAKER_FOOTERRANK_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2, "published");
    }

    @Test(description = "WN-300 : Checks that 'Excluded countries' and 'Currency accepted' tags lists are not " +
            "expanded upon bookmaker change(WAF-523)")
    public void checkAllRestrictedCountriesAreNotExpandedOnBookmakerChange() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries"
                , "'All Countries' was not selected!!!");
        bookmakersTablePage.selectBookmakerFromBookmakersTable(BookmakerApiCalls.bookmaker_Name_1);
        reviewPage.clickOnNavBarItem("Additional Info");
        reviewPage.clickOnShowMoreButtonByFieldName("Excluded countries");
        Assert.assertTrue(reviewPage.getCaptionOfShowMoreButton().get(0).equals("HIDE")
                , "Excluded countries are not expanded");
        reviewPage.clickOnShowMoreButtonByFieldName("Currency Accepted");
        Assert.assertTrue(reviewPage.getCaptionOfShowMoreButton().get(1).equals("HIDE")
                , "Excluded currencies are not expanded");

        footerPage.clickOnReviewsSubMenuElement(BookmakerApiCalls.bookmaker_Name_2 );
        reviewPage.clickOnNavBarItem("Additional Info");
        Assert.assertTrue(reviewPage.getCaptionOfShowMoreButton().get(0).equals("SHOW MORE")
                , "Excluded countries are expanded: should not be.");
        Assert.assertTrue(reviewPage.getCaptionOfShowMoreButton().get(1).equals("SHOW MORE")
                , "Excluded currencies are expanded: should not be.");
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
    }
}
