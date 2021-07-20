package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterByCurrencyAcceptedSection extends BaseTest {

    @Test(description = "WN-264 (OK) Bookmakers filter by Currency Accepted section (WAF-167)")
    public void filterByCurrencyAcceptedSection() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Currency Accepted");
        filterPage.fillValueOnFilterSection("Currency Accepted", "GBP");
        filterPage.selectCheckbox("Currency Accepted", "GBP");
        filterPage.clickOnShowAllButtonBySectionName("Currency Accepted");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("betway", "info-test")));
        filterPage.fillValueOnFilterSection("Currency Accepted", "SWF");
        filterPage.selectCheckbox("Currency Accepted", "SWF");
        bookmakersTablePage.checkEmptyBookmakersTable();
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }
}
