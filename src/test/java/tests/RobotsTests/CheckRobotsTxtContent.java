package tests.RobotsTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckRobotsTxtContent extends BaseTest {

    @Test(description = "Check Robots.txt content")
    public void checkRobotsTxtContent() {
        mainPage.openUrl(appConfig.getBaseUrl() + "/robots.txt");
        System.out.println(mainPage.getPageSource());
        Assert.assertEquals(mainPage.getPageSource(), "<html><head></head><body><pre style=\"word-wrap: break-word; white-space: pre-wrap;\">User-agent: *\n" +
                "Allow: /review/*\n" +
                "Allow: /best-sports-betting-sites\n" +
                "Allow: /best-esports-betting-sites\n" +
                "Allow: /bookmaker-comparison\n" +
                "Allow: /choose-your-bookmaker\n" +
                "Allow: /terms-of-service\n" +
                "Allow: /privacy-policy\n" +
                "Allow: /contact\n" +
                "Allow: /\n" +
                "Allow: /best-csgo-betting-sites\n" +
                "Allow: /best-dota2-betting-sites\n" +
                "Allow: /best-lol-betting-sites\n" +
                "Allow: /about-us\n" +
                "Allow: /ranking-methodology\n" +
                "Allow: /analysis-and-predictions/*\n" +
                "Allow: /all-bookmakers\n" +
                "Allow: /analysis-and-predictions\n" +
                "Disallow: /best-sports-betting-sites?\n" +
                "Disallow: /bookmaker-comparison?\n" +
                "Disallow: /choose-your-bookmaker?\n" +
                "Disallow: /best-csgo-betting-sites?\n" +
                "Disallow: /best-dota2-betting-sites?\n" +
                "Disallow: /best-lol-betting-sites?\n" +
                "Disallow: /best-esports-betting-sites?\n" +
                "Disallow: /?\n" +
                "Disallow: /analysis-and-predictions?countries*\n" +
                "Disallow: /all-bookmakers?\n" +
                "\n" +
                "# Sitemaps\n" +
                "Sitemap: https://winners.net/sitemap.xml</pre></body></html>");
    }
}
