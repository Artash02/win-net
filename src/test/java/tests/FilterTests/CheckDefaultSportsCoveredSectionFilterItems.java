package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckDefaultSportsCoveredSectionFilterItems extends BaseTest {

    @Test(description = "Check default filter items in Sports Covered section")
    public void checkDefaultSportsCoveredSectionFilterItems() throws InterruptedException {
        filterPage.clickOnExpandIconsBySectionName("Sports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Collections.emptyList()));
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"),
                new ArrayList<>(Arrays.asList("Tennis", "Football", "Basketball")));
        softAssert.assertAll();
    }
}
