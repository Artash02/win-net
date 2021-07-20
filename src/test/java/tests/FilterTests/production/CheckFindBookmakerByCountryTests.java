package tests.FilterTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFindBookmakerByCountryTests extends BaseTest {

    @Test(description = "(OK) Countries and States filter (WAF-309)")
    public void bookmakerFilterByCountry() {
        filterPage.selectCountryInFilterSection("Albania");
        Assert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Arrays.asList("New_Line_Bookmaker", "book 3")));
    }

}
