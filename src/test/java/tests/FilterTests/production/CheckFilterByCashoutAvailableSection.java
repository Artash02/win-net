package tests.FilterTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFilterByCashoutAvailableSection extends BaseTest {

    @Test(description = "WN-91 (OK) Bookmakers filter by Cashout Available section (WAF-167)")
    public void filterByCashoutAvailableSection() {
        Assert.assertTrue(mainPage.numberOfElementsLocated(8));
        filterPage.clickOnExpandIconsBySectionName("Cashout Available");
        filterPage.selectValueForCashoutAvailableSection("Yes");
        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("betway", "My", "book 3", "POKER")));
    }
}
