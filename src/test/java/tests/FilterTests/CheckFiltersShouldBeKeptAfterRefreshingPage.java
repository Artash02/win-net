package tests.FilterTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CheckFiltersShouldBeKeptAfterRefreshingPage extends BaseTest {


    @Test(description = "Check Filters should be kept after refreshing page")
    public void checkFiltersShouldBeKeptAfterRefreshingPage() throws InterruptedException {
        filterPage.clickOnExpandIconsBySectionName("Cashout Available");
        filterPage.selectValueForCashoutAvailableSection("Yes");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.getCheckedCashoutAvailableSectionItem(), "Yes");

        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "League of Legends");
        filterPage.selectCheckbox("Esports Covered", "League of Legends");
        mainPage.refreshPage();
        filterPage.clickOnExpandIconsBySectionName("Esports Covered");
        filterPage.clickOnShowAllButtonBySectionName("Esports Covered");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.singletonList("League of Legends")));

        filterPage.clickOnExpandIconsBySectionName("License Country");
        filterPage.selectCountryInLicenseCountry("Albania");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.getSelectedLicenseCountry(), "Albania");
    }
}
