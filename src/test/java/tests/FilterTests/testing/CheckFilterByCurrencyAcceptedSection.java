package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Log;

public class CheckFilterByCurrencyAcceptedSection extends BaseTest {

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

    @Test(description = "WN-264 (OK) Bookmakers filter by Currency Accepted section (WAF-167)")
    public void filterByCurrencyAcceptedSection() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Currency Accepted");
        filterPage.fillValueOnFilterSection("Currency Accepted", "RUB");
        filterPage.selectCheckbox("Currency Accepted", "RUB");
        filterPage.clickOnShowAllButtonBySectionName("Currency Accepted");
        System.out.println("FRONT  " + bookmakersTablePage.getBookmakersCount());
        System.out.println("BACK  " + bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Currencies",
                "RUB", "Armenia"));
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(),
                bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Currencies",
                        "RUB", "Armenia"));
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        Assert.assertEquals(bookmakersTablePage.getBookmakersName(),
                bookmakerApiCalls.getBookmakersNamesByCountryAndFilterCriteria("Currencies", "RUB", "Armenia"));
        softAssert.assertAll();
    }

    @AfterTest(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
