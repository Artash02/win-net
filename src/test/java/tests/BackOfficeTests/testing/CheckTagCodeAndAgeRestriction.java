package tests.BackOfficeTests.testing;

import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckTagCodeAndAgeRestriction extends BaseTest {

    @BeforeMethod()
    public void setTagCodeAndAgeRestrictionForCountry() throws Exception {
        backOfficeApiCalls.setCountryCodeAndAge();
    }

    @Test(description = "Check tag code and age restriction functionality for Country tag")
    public void checkTagCodeAndAgeRestriction() {
        filterPage.clickOnBestBettingSitesForDropdown();
        filterPage.selectCountryByCountryCode("zmb");
        Assert.assertEquals(mainPage.getAgeRestriction(), "21");
    }

    @AfterMethod(description = "Unset tag code and age restriction", alwaysRun = true)
    public void unsetTagCodeAndAgeRestriction() throws Exception {
        backOfficeApiCalls.unsetCountryCodeAndAge();
    }
}
