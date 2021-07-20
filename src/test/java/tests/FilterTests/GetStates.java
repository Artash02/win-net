package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class GetStates extends BaseTest {

    @Test(description="Get states")
    public void getStates() {
        filterPage.selectCountryInHeader("United States");
        softAssert.assertEquals(filterPage.getStates(), new ArrayList<>(Arrays.asList("All States", "Minnesota",
                "Alaska", "Alabama", "Iowa", "Texas", "New Jersay", "Philadelphia", "Arkansas", "Kentucky")));
    }
}
