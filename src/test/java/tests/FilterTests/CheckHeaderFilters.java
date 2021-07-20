package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckHeaderFilters extends BaseTest {

    @Test(description = "Check default filter items when user change page")
    public void checkHeaderFiltersChecking() throws InterruptedException {
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")));
        mainPage.selectTabInHeader("Bookmaker Comparison");
        filterPage.clickOnExpandIconsBySectionName("Sports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Collections.emptyList()));
        softAssert.assertAll();
    }
}
