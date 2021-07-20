package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckEsportsCoveredFilterItemsAfterPageRefresh extends BaseTest {

    @Test(description = "(OK) 'ESports Covered' Filter section items after page refreshing (WAF-191)")
    public void checkEsportsCoveredFilterItemsAfterPageRefresh() throws InterruptedException {
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Arrays.asList("Dota 2", "LoL", "CS:GO")));
        mainPage.refreshPage();
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Arrays.asList("Dota 2", "LoL", "CS:GO")));

        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("CS:GO")));
        mainPage.refreshPage();
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("CS:GO")));

        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("Dota 2")));
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("Dota 2")));

        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("LoL")));
        mainPage.refreshPage();
        softAssert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("LoL")));
        softAssert.assertAll();
    }
}
