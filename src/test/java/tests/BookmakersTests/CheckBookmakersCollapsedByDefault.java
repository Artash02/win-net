package tests.BookmakersTests;

import org.junit.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckBookmakersCollapsedByDefault extends BaseTest {

    @Test(description = "Check bookmakers collapsed by default")
    public void checkBookmakersCollapsedByDefault() {
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectBestEsportsBettingSites("All Esports Betting Sites");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        mainPage.selectTabInHeader("Bookmaker Comparison");
        comparisonPage.chooseComparisonSubTabByName("List");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        Assert.assertTrue("All bookmakers is not collapsed", bookmakersTablePage.isAllBookmakersCollapsed());
    }
}
