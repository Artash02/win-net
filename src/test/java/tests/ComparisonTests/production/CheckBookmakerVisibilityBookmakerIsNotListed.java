package tests.ComparisonTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.Collections;


public class CheckBookmakerVisibilityBookmakerIsNotListed extends BaseTest {

    @Test(description = "WN-134 Checking bookmakers visibility in comparison chart when bookmaker is not available in list")
    public void checkBookmakerVisibilityBookmakerIsNotListed() {

        mainPage.openUrl(baseUrl + "/review/5ef5e90dbdb0620010edb96c/betmgm");
        reviewPage.clickOnCompareWithOthersButton();
        Assert.assertEquals(comparisonPage.getComparisonChartCoordinates(), Collections.emptyList(),"Chart is not empty");
    }
}