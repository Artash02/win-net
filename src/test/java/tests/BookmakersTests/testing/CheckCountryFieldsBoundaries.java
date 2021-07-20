package tests.BookmakersTests.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCountryFieldsBoundaries extends BaseTest {

    @Test(description = "Check country field boundaries")
    public void checkCountryFieldBoundaries() {
        filterPage.selectCountryInHeader("Palestinian Territory, Occupied");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(),"Palestinian Territory, Occupied");
        Assert.assertEquals(filterPage.getSelectedCountryInSubHeader(), "Palestinian Territory, Occupied");
        filterPage.selectCountryInHeader("North Macedonia, Republic of");
        Assert.assertEquals(filterPage.getSelectedCountryInFilter(),"North Macedonia, Republic of");
        Assert.assertEquals(filterPage.getSelectedCountryInSubHeader(), "North Macedonia, Republic of");
    }
}
