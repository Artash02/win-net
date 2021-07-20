package tests.BookmakersTests.testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckForAllBookmakersShowMoreButtonIsNotVisible extends BaseTest {

    @Test(description = "Check for all bookmakers show more button is not visible")
    public void checkForAllBookmakersShowMoreButtonIsNotVisible() throws InterruptedException {
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        System.out.println(bookmakerApiCalls.getBookmakersCount());
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCount());
        Assert.assertFalse(bookmakersTablePage.isShowAllBookmakersButtonVisible(), "Button are visible");
    }
}
