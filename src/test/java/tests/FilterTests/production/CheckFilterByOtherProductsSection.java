package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterByOtherProductsSection extends BaseTest {

    @Test(description = "WN-99 (OK) Bookmakers filter by Other Products section")
    public void filterByOtherProductsSection() {
        filterPage.clickOnSearchIconsBySectionName("Other Products");
        filterPage.fillValueOnFilterSection("Other Products", "Live Casino");
        filterPage.selectCheckbox("Other Products", "Live Casino");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("betway", "November, 2020")));
        filterPage.fillValueOnFilterSection("Other Products", "Virtual Sports");
        filterPage.selectCheckbox("Other Products", "Virtual Sports");
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }

}
