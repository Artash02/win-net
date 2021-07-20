package tests.BookmakersTests.production;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakersLimitIsTen extends BaseTest {

    @Test(description = "Limit of bookmakers displayed by default is 10")
    public void checkLimitOfBookmakersIsTen() throws InterruptedException {
        mainPage.clickOnHomepageIcon();
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCount());

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);

        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.chooseComparisonSubTabByName("List");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCount());

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);

        comparisonPage.chooseComparisonSubTabByName("Comparison");
        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), bookmakerApiCalls.getBookmakersCount());

        Assert.assertEquals(bookmakersTablePage.getBookmakersCount(), 10);
    }
}
