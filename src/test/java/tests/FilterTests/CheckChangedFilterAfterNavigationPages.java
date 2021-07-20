package tests.FilterTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckChangedFilterAfterNavigationPages extends BaseTest {

    @Test(description = "Check changed filter after navigation")
    public void checkChangedFilterAfterNavigationPages() {
        filterPage.selectCountryInFilterSection("All Countries");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries");
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertEquals(mainPage.getCurrentUrl(),appConfig.getBaseUrl() + "/betting-tips?countries=__all__");
        analysisPage.selectArticleByNumber(1);
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "All Countries");
        }
    }

