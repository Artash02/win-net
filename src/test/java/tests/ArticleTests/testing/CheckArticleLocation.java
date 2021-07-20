package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleLocation extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-158 : (WAF-296) Checking new created and published article should be first article in " +
            "Betting Tips page")
    public void checkArticleLocation() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        Assert.assertEquals(analysisPage.getFirstArticleName(), ArticleApiCalls.articleName);
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void AfterTest() {
        articleApiCalls.deleteArticle();
    }
}
