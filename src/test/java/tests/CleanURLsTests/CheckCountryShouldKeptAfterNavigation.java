package tests.CleanURLsTests;

import api.ArticleApiCalls;
import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCountryShouldKeptAfterNavigation extends BaseTest {
    @BeforeMethod(description = "Create article")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-238 : (OK) Country should kept after navigation")
    public void CheckingCountryKeptAfterNavigation () throws InterruptedException {
        filterPage.selectCountryInHeader("All Countries");
        mainPage.selectTabInHeader("Bookmaker Comparison");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Bookmaker Comparison"));
        Assert.assertEquals(filterPage.countryIs(), "All Countries",
                "The country is not kept after page change");
        mainPage.refreshPage();
        Assert.assertEquals(filterPage.countryIs(), "All Countries",
                "The country is not kept after refresh");
        mainPage.selectTabInHeader("Betting Tips");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"));
        analysisPage.selectArticleByNumber(0);
        mainPage.waitToTitleContains(ArticleApiCalls.articleName);
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        Assert.assertEquals(filterPage.countryIs(), "All Countries",
                "The country is not kept after coming to homepage from the article page");
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        articleApiCalls.deleteArticle();
    }
}
