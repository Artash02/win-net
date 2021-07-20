package tests.BookmakersTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckCountriesConnectivityWn4 extends BaseTest {

    @Test(description = "Check countries connectivity")
    public void checkCountriesConnectivity() {
        filterPage.selectCountryInHeader("American Samoa");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(), "American Samoa");
        filterPage.selectCountryInFilterSection("Bolivia");
        Assert.assertEquals(filterPage.getSelectedCountryInSubHeader(),"Bolivia");
    }
}
