package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterByPaymentAcceptedSection extends BaseTest {

    @Test(description = "WN-90 (OK) Bookmakers filter by Payment Accepted section")
    public void filterByPaymentAcceptedSection() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Payment Accepted");
        filterPage.clickOnShowAllButtonBySectionName("Payment Accepted");
        filterPage.fillValueOnFilterSection("Payment Accepted", "Visa Electron");
        filterPage.selectCheckbox("Payment Accepted", "Visa Electron");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("book 3", "info-test")));
        filterPage.fillValueOnFilterSection("Payment Accepted", "Click2Pay");
        filterPage.selectCheckbox("Payment Accepted", "Click2Pay");
        bookmakersTablePage.checkEmptyBookmakersTable();
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }
}
