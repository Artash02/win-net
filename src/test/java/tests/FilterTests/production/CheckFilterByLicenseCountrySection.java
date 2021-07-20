package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CheckFilterByLicenseCountrySection extends BaseTest {

    @Test(description = "WN-263 (OK) Bookmakers filter by License Country section")
    public void filterByLicenseCountrySection() {
        filterPage.clickOnExpandIconsBySectionName("License Country");
        filterPage.selectCountryInLicenseCountry("Albania");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("Bonus Test", "November, 2020")));
        filterPage.selectCountryInLicenseCountry("American Samoa");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList("")));
        softAssert.assertAll();
    }


}
