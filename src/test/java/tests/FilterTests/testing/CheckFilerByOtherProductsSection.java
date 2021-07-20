package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Log;

public class CheckFilerByOtherProductsSection extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Bookmaker bonus, 5. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "WN-99 (OK) Bookmakers filter by Other Products section")
    public void filterByOtherProductsSection() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Other Products");
        filterPage.fillValueOnFilterSection("Other Products", "Live Casino");
        filterPage.selectCheckbox("Other Products", "Live Casino");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(),
                bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Others",
                        "Live Casino", "Armenia"));
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        Assert.assertEquals(bookmakersTablePage.getBookmakersName(),
                bookmakerApiCalls.getBookmakersNamesByCountryAndFilterCriteria("Others", "Live Casino", "Armenia"));
    }

    @AfterTest(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
