package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckSportsCoveredFilterItemsAfterPageRefresh extends BaseTest {


    @Test(description = "(OK) 'Sports Covered' Filter section items after page refreshing (WAF-191)")
    public void checkSportsCoveredFilterItemsAfterPageRefresh() throws InterruptedException {
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"), new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")));
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"), new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")));
        softAssert.assertAll();
    }
}
