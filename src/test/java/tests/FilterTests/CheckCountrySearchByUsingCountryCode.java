package tests.FilterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckCountrySearchByUsingCountryCode extends BaseTest {

    @Test(description="Check country search by using Country code")
    public void checkCountrySearchByCountryCode() {
        filterPage.clickOnBestBettingSitesForDropdown();
        Assert.assertEquals(filterPage.getFilteredCountry("AS"), "American Samoa");
        Assert.assertEquals(filterPage.getFilteredCountry("NC"), "New Caledonia");
        Assert.assertEquals(filterPage.getFilteredCountry("VE"), "Venezuela");
        Assert.assertEquals(filterPage.getFilteredCountry("ALG"), "Algeria");
    }
}
