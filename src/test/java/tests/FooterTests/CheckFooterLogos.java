package tests.FooterTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class CheckFooterLogos extends BaseTest {

    @Test(description = "WN-309 : Checks if clicking on the logos will open the correct homepage in a new tab.(WAF-521;WAF-520)")
    public void checkFooterLogos() {
        footerPage.clickOnLogoElementAndPassToMentionedTab(footerPage.winIconElement,1);
        Assert.assertTrue(mainPage.getPageUrl().contains("https://win.gg/"),"Wrong Page is Displayed.");
        footerPage.returnToHomeTab();
        footerPage.clickOnLogoElementAndPassToMentionedTab(footerPage.winnersBetLogoIconElement,2);
        Assert.assertTrue(mainPage.getPageUrl().contains("https://winners.bet/welcome"),"Wrong Page is Displayed.");
        footerPage.returnToHomeTab();
        footerPage.clickOnLogoElementAndPassToMentionedTab(footerPage.winnersLeagueIconElement,3);
        Assert.assertTrue(mainPage.getPageUrl().contains("https://winnersleague.gg/"),"Wrong Page is Displayed.");
    }
}
