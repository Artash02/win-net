package tests.BookmakersTests.production;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckExpandIconFunctionality extends BaseTest {

    @Test(description = "Check 'Expand Icon' functionality in Bookmakers list")
    public void checkExpandIconFunctionality() throws InterruptedException {
        bookmakersTablePage.clickOnButtonsByBookmakerNameOnSportsBettingSitesPage("VBET", "Expand");
        bookmakersTablePage.clickOnButtonsByBookmakerNameOnSportsBettingSitesPage("Unibet", "Expand");
        Assert.assertFalse(bookmakersTablePage.isBookmakerExpanded("VBET"));
    }
}
