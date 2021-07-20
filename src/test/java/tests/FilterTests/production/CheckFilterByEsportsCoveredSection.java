package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterByEsportsCoveredSection extends BaseTest {

    @Test(description = "WN-88 (OK) Bookmakers filter by Esports-Covered section")
    public void filterByEsportsCoveredSection() {
        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "LoL");
        filterPage.selectCheckbox("Esports Covered", "LoL");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("book 3", "Rallye")));
        filterPage.fillValueOnFilterSection("Esports Covered", "League of Legends");
        filterPage.selectCheckbox("Esports Covered", "League of Legends");
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }
}
