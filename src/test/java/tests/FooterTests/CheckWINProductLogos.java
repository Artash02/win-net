package tests.FooterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckWINProductLogos extends BaseTest {
    @Test(description = "WN-294 : (OK) WIN products logos")
    public void checkWINProductsPart() {
        softAssert.assertEquals(footerPage.getWINProductsText(), "We are part of the WIN Group of esports companies",
                "Win products text is wrong on the footer");
        footerPage.clickToWINLogos("win");
        mainPage.handleWindowByTitle("WIN.gg: Esports news, scores, stats, and schedules");
        softAssert.assertEquals(mainPage.getCurrentUrl(), "https://win.gg/",
                "The win.gg product URL is wrong");
        mainPage.handleWindowByTitle("Find the best betting sites, bonuses and features - Winners.net");
        footerPage.clickToWINLogos("winnersBetLogo");
        mainPage.handleWindowByTitle("Welcome to Winners.bet");
        softAssert.assertEquals(mainPage.getCurrentUrl(), "https://winners.bet/welcome?btag=269575_l100913",
                "The winners.bet product URL is wrong");
        mainPage.handleWindowByTitle("Find the best betting sites, bonuses and features - Winners.net");
        footerPage.clickToWINLogos("winnersLeague");
        mainPage.handleWindowByTitle("WINNERS League - winnersleague.gg: news, streams, scores, and stats.");
        softAssert.assertEquals(mainPage.getCurrentUrl(), "https://winnersleague.gg/",
                "The winnersLeague product URL is wrong" );
        softAssert.assertAll();
    }
}
