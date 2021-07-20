package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Log;

public class CheckFilterByEsportsCoveredSection extends BaseTest {

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

    @Test(description = "Check Bookmaker filter by Esports Covered section")
    public void checkFilterByEsportsCoveredSection() throws InterruptedException {
        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "CS:GO");
        filterPage.selectCheckbox("Esports Covered", "CS:GO");
        System.out.println("FRONT  " + bookmakersTablePage.getBookmakersCount());
        System.out.println("BACK  " + bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Esports",
                "CS:GO", "Armenia"));
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(),
                bookmakerApiCalls.getBookmakersCountFromApiByCountryAndFilterCriteria("Esports",
                        "CS:GO", "Armenia"), "HERE IS THE MESSAGE YOU ARE LOOKING FOR!!!!!!!");
        bookmakersTablePage.selectValueInSortByDropDown("Bookmaker");
        org.testng.Assert.assertEquals(bookmakersTablePage.getBookmakersName(),
                bookmakerApiCalls.getBookmakersNamesByCountryAndFilterCriteria("Esports", "CS:GO", "Armenia"));
    }

    @AfterTest(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
