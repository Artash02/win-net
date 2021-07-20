package tests.FilterTests.testing;

import api.BookmakerApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

import java.util.ArrayList;
import java.util.Collections;

public class CheckBookmakerFilterFunctionalityWithTwentyTwoEsportsTags extends BaseTest {


    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Add Data in Bookmaker Profile section" +
            "4. Add Best Bonus for bookmaker, 5. Add 22 EsportsTags in 'Review section', 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerWithTwentyTwoEsportTags(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");
    }

    @Test(description = "Check bookmaker filter with 22 Esports tags")
    public void checkBookmakerFilterFunctionalityWithTwentyTwoEsportsTags() throws InterruptedException {
//        Assert.assertEquals(bookmakerApiCalls.getBookmakersGiven22Parameters(), 409);
        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "tag-1");
        filterPage.selectCheckbox("Esports Covered", "tag-1");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-2");
        filterPage.selectCheckbox("Esports Covered", "tag-2");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-3");
        filterPage.selectCheckbox("Esports Covered", "tag-3");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-4");
        filterPage.selectCheckbox("Esports Covered", "tag-4");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-5");
        filterPage.selectCheckbox("Esports Covered", "tag-5");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-6");
        filterPage.selectCheckbox("Esports Covered", "tag-6");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-7");
        filterPage.selectCheckbox("Esports Covered", "tag-7");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-8");
        filterPage.selectCheckbox("Esports Covered", "tag-8");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-9");
        filterPage.selectCheckbox("Esports Covered", "tag-9");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-10");
        filterPage.selectCheckbox("Esports Covered", "tag-10");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-11");
        filterPage.selectCheckbox("Esports Covered", "tag-11");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-12");
        filterPage.selectCheckbox("Esports Covered", "tag-12");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-13");
        filterPage.selectCheckbox("Esports Covered", "tag-13");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-14");
        filterPage.selectCheckbox("Esports Covered", "tag-14");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-15");
        filterPage.selectCheckbox("Esports Covered", "tag-15");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-16");
        filterPage.selectCheckbox("Esports Covered", "tag-16");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-17");
        filterPage.selectCheckbox("Esports Covered", "tag-17");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-18");
        filterPage.selectCheckbox("Esports Covered", "tag-18");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-19");
        filterPage.selectCheckbox("Esports Covered", "tag-19");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-20");
        filterPage.selectCheckbox("Esports Covered", "tag-20");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList(BookmakerApiCalls.bookmakerName)));
        filterPage.fillValueOnFilterSection("Esports Covered", "tag-21");
        filterPage.selectCheckbox("Esports Covered", "tag-21");

        filterPage.fillValueOnFilterSection("Esports Covered", "tag-22");
        filterPage.selectCheckbox("Esports Covered", "tag-22");

        filterPage.fillValueOnFilterSection("Esports Covered", "PUBG");
        filterPage.selectCheckbox("Esports Covered", "PUBG");
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }

    @AfterMethod(description = "Delete created Bookmaker", alwaysRun = true)
    public void AfterTest() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
