package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckIfQuickCardContainingRestrictedCountryIsDisplayed extends BaseTest {

    @BeforeMethod(description = "Create 1 bookmaker")
    public void createBookmakers() throws Exception {

        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmakerId);
        quickCardApiCalls.addQuickCardInfo(" Romania-is-a-Restricted-Country", 1
                , BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId, "published");
    }

    @Test(description = "WN-304 : Check that a Quick-card containing Restricted Country Name is displayed(WAF-515) ")
    public void checkIfQuickCardContainingRestrictedCountryIsDisplayed() throws InterruptedException {

        filterPage.selectCountryInHeader("All Countries");

        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Home Page");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Comparison Page");
//        bookmakersTablePage.clickOnButtonsByBookmakerName(BookmakerApiCalls.bookmakerName, "Full Review");
//        filterPage.selectCountryInHeader("All Countries");
//        Assert.assertEquals(quickCardPage.getQuickCardInfo(1), BookmakerApiCalls.bookmakerName + "," +
//                " 6.7 / 10, Romania-is-a-Restricted-Country, REVIEW, WEBSITE", "Not Present on Review Page");
        mainPage.selectTabInHeader("Betting Tips");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Betting Tips Page");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(),"Not Present on Best Sports Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Best CS:GO Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Best Dota2 Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Best LoL Betting Sites Page");
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Footer-All Bookmakers Page");

        mainPage.clickOnHomepageIcon();
        filterPage.selectCountryInHeader("Romania");

        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Home Page");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Comparison Page");
        mainPage.selectTabInHeader("Betting Tips");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Betting Tips Page");
        mainPage.selectTabInHeader("Best Sports Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        filterPage.selectCountryInHeader("Romania");
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Best Sports Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Best CS:GO Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Best Dota2 Betting Sites Page");
        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertFalse(quickCardPage.isElementPresent(), "Present on Best LoL Betting Sites Page");
        footerPage.clickOnBookmakersSubMenuPages("All Bookmakers");
        mainPage.waitToProgressBarIsNotDisplayed();
        Assert.assertTrue(quickCardPage.isElementPresent(), "Not Present on Footer-All Bookmakers Page");
    }

    @AfterMethod(alwaysRun = true, description = "Delete created Bookmakers")
    public void deleteCreatedBookmakers() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
    }
}
