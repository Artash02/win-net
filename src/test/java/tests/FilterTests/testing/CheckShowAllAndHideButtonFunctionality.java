package tests.FilterTests.testing;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckShowAllAndHideButtonFunctionality extends BaseTest {

    @Test(description = "(OK) Checking Show all/Hide buttons working in bookmaker filters (WAF-345 $ WAF-194)")
    public void checkShowAndHideButtonFunctionality() throws InterruptedException {
        filterPage.clickOnExpandIconsBySectionName("Payment Accepted");
        softAssert.assertEquals(filterPage.getFilterItemsCountBySectionName("Payment Accepted"), 5);
        filterPage.clickOnShowAllButtonBySectionName("Payment Accepted");
        softAssert.assertEquals(filterPage.getFilterItemsCountBySectionName("Payment Accepted"), 14);
        softAssert.assertAll();
    }
}
