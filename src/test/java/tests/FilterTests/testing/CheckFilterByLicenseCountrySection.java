package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckFilterByLicenseCountrySection extends BaseTest {


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

    @Test(description = "WN-92 : (OK) Bookmakers filter by Language section")
    public void filterByLicenseCountry() throws Exception {
        filterPage.clickOnExpandIconsBySectionName("License Country");
        filterPage.selectCountryInLicenseCountry("Algeria");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(),
                bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Licenses",
                        "Algeria", "Armenia"));
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        org.testng.Assert.assertEquals(bookmakersTablePage.getBookmakersName(),
                bookmakerApiCalls.getBookmakersNamesByCountryAndFilterCriteria("Licenses", "Algeria", "Armenia"));
    }

    @AfterTest(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
