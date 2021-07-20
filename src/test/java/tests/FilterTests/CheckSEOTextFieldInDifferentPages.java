package tests.FilterTests;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckSEOTextFieldInDifferentPages extends BaseTest {

    @Test(description = "WN-317 : SEO text field (WAF-534)")
    public void checkSEOTextFieldInDifferentPages() throws Exception {

        mainPage.selectTabInHeader("Best Sports Betting Sites");
        Assert.assertEquals(footerPage.getSeoTitle(), "BEST SPORTS BETTING SITES"
                , "Wrong Title in 'Best Sports Betting Sites' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Best Sports Betting Sites' page.");

        mainPage.selectBestEsportsBettingSites("Best CS:GO Betting Sites");
        Assert.assertEquals(footerPage.getSeoTitle(), "BEST CS:GO BETTING SITES"
                , "Wrong Title in 'Best CS:GO Betting Sites' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Best CS:GO Betting Sites' page.");

        mainPage.selectBestEsportsBettingSites("Best Dota2 Betting Sites");
        Assert.assertEquals(footerPage.getSeoTitle(), "BEST DOTA 2 BETTING SITES"
                , "Wrong Title in 'Best Dota2 Betting Sites' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Best Dota2 Betting Sites' page.");

        mainPage.selectBestEsportsBettingSites("Best LoL Betting Sites");
        Assert.assertEquals(footerPage.getSeoTitle(), "BEST LOL BETTING SITES"
                , "Wrong Title in 'Best LoL Betting Sites' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Best LoL Betting Sites' page.");

        mainPage.selectTabInHeader("Bookmaker Comparison");
        Assert.assertEquals(footerPage.getSeoTitle(), "BOOKMAKER COMPARISON"
                , "Wrong Title in 'Bookmaker Comparison' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Bookmaker Comparison' page.");

        mainPage.selectTabInHeader("Bookmaker Recommender");
        Assert.assertEquals(footerPage.getSeoTitle(), "BOOKMAKER RECOMMENDER"
                , "Wrong Title in 'Bookmaker Recommender' page.");
        Assert.assertEquals(footerPage.clickOnTextExpandButton(), "SHOW LESS"
                , " Error on 'Read Less' in 'Bookmaker Recommender' page.");
    }
}
