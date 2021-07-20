package tests.BookmakersTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckBookmakerBonusValueSizeIs6 extends BaseTest {


    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add  six digits in bookmaker bonus value field")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addIncorrectBookmakerBonusValue(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "Check bonus value max size is 5")
    public void checkBonusValueMaxSizeIs5() throws Exception {
        filterPage.selectCountryInFilterSection("All Countries");
        Assert.assertEquals(bookmakersTablePage.getBookmakerInfoByColumnName(BookmakerApiCalls.bookmakerName, "Bonus"), "50008", "Bonus field should be contains max 5 digits");
    }

    @AfterMethod(alwaysRun = true, description = "Delete Created Bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
