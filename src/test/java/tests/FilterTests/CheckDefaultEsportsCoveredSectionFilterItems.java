package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckDefaultEsportsCoveredSectionFilterItems extends BaseTest {

    @Test(description = "Check default filter items in 'Esports' covered section")
    public void checkDefaultEsportsCoveredSectionFilterItems() throws InterruptedException {
        filterPage.clickOnExpandIconsBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Collections.emptyList()));
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Arrays.asList("Dota 2", "LoL", "CS:GO")));
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Collections.singletonList("CS:GO")));
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Collections.singletonList("Dota 2")));
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"),
                new ArrayList<>(Collections.singletonList("LoL")));
        softAssert.assertAll();
    }
}
