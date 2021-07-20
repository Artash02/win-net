package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterBySportsCoveredSection extends BaseTest {

    @Test(description = "WN-86 (OK) Bookmakers filter by Sports Covered section")
    public void filterBySportsCoveredSection() {
        filterPage.clickOnSearchIconsBySectionName("Sports Covered");
        filterPage.fillValueOnFilterSection("Sports Covered", "football");
        filterPage.selectCheckbox("Sports Covered", "Football");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("betway", "New_Line_Bookmaker", "winners-bonus", "POKER", "Rallye")));
        filterPage.fillValueOnFilterSection("Sports Covered", "Darts");
        filterPage.selectCheckbox("Sports Covered", "Darts");
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }
}
