package tests.BackOfficeTests.testing;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckBookmakersSortingByColumnNames extends BaseTest {

    @Test(description = "Check sorting by column names")
    public void checkSortingByColumnNames() throws Exception {
        mainPage.openUrl(appConfig.getBackOfficeUrl());
        backOfficePage.fillEmail(appConfig.getBackofficeUsername());
        backOfficePage.fillPassword(appConfig.getBackOfficePassword());
        backOfficePage.clickOnLoginButton();
        mainPage.openUrl(appConfig.getBackOfficeUrl() + "/winners-net/bookmakers");
        Thread.sleep(3000);
        backOfficePage.selectColumnByName("Name");
        Thread.sleep(3000);
        Assert.assertTrue(backOfficePage.checkIfDescendingSortedBookmakerNames(backOfficePage.getBookmakerItemsByName("Name")));
        backOfficePage.selectColumnByName("Name");
        Thread.sleep(3000);
        Assert.assertTrue(backOfficePage.checkIfAscendingSortedBookmakerNames(backOfficePage.getBookmakerItemsByName("Name")));
        backOfficePage.selectColumnByName("Rating");
        Thread.sleep(3000);
        Assert.assertTrue(backOfficePage.checkIfRatingSorted(backOfficePage.getBookmakerItemsByName("Rating")));
        Thread.sleep(2000);
        backOfficePage.selectColumnByName("Last Updated");
        Thread.sleep(2000);
        Assert.assertTrue(backOfficePage.checkIfDatesDescendingSorted(backOfficePage.getBookmakerItemsByName("Last Updated")));
        Thread.sleep(3000);
    }
}

