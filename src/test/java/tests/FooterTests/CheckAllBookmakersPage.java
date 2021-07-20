package tests.FooterTests;

import api.BookmakerApiCalls;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.IOException;

public class CheckAllBookmakersPage extends BaseTest {
    @BeforeMethod(description = "1. Create 3 bookmaker with different footer rank")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-1", 1
                , BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_2, "published");
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_3);
        quickCardApiCalls.addQuickCardInfo("Text-to-display-test-4", 4
                , BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_3, "published");
    }
    
    @Test(description = "WN-283 : OK for all bookmakers")
    public void checkAllBookmakersPage() throws IOException, ParseException {
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("All Bookmakers"));
        Assert.assertTrue(mainPage.urlContains("all-bookmakers"), "The page url is wrong");
//        softAssert.assertEquals(quickCardPage.quickCardCount(), bookmakerApiCalls.getQuickCardsCountFromApiByCountry(null));
        softAssert.assertEquals(mainPage.getPageSubHeaderText(), "All Bookmakers",
                "The all bookmakers page header text is incorrect");
        softAssert.assertEquals(mainPage.getPageDescriptionText(), "On Winners.net you can find the best reviews for all bookmakers, " +
                "all in one place. Sports or esports, Winners.net offers a great selection of bookmakers that cover them all. " +
                "Read, compare, review, and choose your favorite betting sites with Winners.net.",
                "The all page description text is incorrect");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(), 3,
                "The bookmakers count is invalid on all bookmakers page");
        softAssert.assertEquals(quickCardPage.quickCardCount(), 2,
                "The bookmakers quick cards count is invalid on all bookmakers page");
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(filterPage.countryIs(), "Armenia",
                "The country is incorrect when getting on homepage from all bookmakers page");
        bookmakerApiCalls.changeBookmakerPublishStatus(BookmakerApiCalls.bookmaker_Id_1, "reviewCreation");
        comparisonPage.chooseComparisonSubTabByName("Comparison");
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("All Bookmakers"));
        softAssert.assertEquals(mainPage.getPageSubHeaderText(), "All Bookmakers",
                "The all bookmakers page header text is wrong");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersCount(), 2,
                "The bookmakers count is wrong on all bookmakers page");
        softAssert.assertEquals(quickCardPage.quickCardCount(), 1,
                "The bookmakers quick cards count is wrong on all bookmakers page");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
    }
}
