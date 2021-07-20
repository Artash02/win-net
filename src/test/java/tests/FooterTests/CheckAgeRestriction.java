package tests.FooterTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;


public class CheckAgeRestriction extends BaseTest {
    @Test(description = "WN-152 : (OK) Footer Age Restriction (WAF-312)")
    public void checkFooterRestrictionItem() throws InterruptedException {
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), backOfficeApiCalls.getAgeRestrictionByCountryNameFromApi("Armenia"),
                "Age restriction is incorrect for Armenia");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        filterPage.selectCountryInHeader("All Countries");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), "18",
                "Age restriction is incorrect for all countries");
        filterPage.selectCountryInHeader("Belgium");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), backOfficeApiCalls.getAgeRestrictionByCountryNameFromApi("Belgium"),
                "Age restriction is incorrect for Belgium");
        filterPage.selectCountryInHeader("Albania");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(), backOfficeApiCalls.getAgeRestrictionByCountryNameFromApi("Albania"),
                "Age restriction is incorrect for Albania");
        filterPage.selectCountryInHeader("United States");
        filterPage.selectCountryStateInHeader("Minnesota");
        Assert.assertEquals(footerPage.getFooterAgeRestriction(),
                backOfficeApiCalls.getAgeRestrictionByCountryNameFromApi("United States", "Minnesota"),
                "Age restriction is incorrect for Minnesota state from US");
    }
}
