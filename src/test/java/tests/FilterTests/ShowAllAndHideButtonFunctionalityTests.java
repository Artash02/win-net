package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ShowAllAndHideButtonFunctionalityTests extends BaseTest {

    @Test(description = "(OK) Checking Show all/Hide buttons working in bookmaker filters (WAF-345 $ WAF-194)")
    public void checkShowAndHideButtonFunctionality() throws InterruptedException {
       filterPage.clickOnExpandIconsBySectionName("Sports Covered");
       softAssert.assertEquals(filterPage.getFilterItemsCountBySectionName("Sports Covered"), 5);
       filterPage.clickOnShowAllButtonBySectionName("Sports Covered");
       softAssert.assertEquals(filterPage.getFilterItemsCountBySectionName("Sports Covered"), 12);
       softAssert.assertAll();
    }
}
