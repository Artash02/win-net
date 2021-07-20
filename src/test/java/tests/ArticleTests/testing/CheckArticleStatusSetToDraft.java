package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckArticleStatusSetToDraft extends BaseTest {
    @BeforeMethod(description = "1. Create article, 2. Insert Media" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Insert Media");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-316 : Checks article status setting to 'Draft'")
    public void checkArticleStatusSetToDraft() throws Exception {
        mainPage.selectTabInHeader("Betting Tips");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        articleApiCalls.changeArticleStatusTo("draft");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        mainPage.refreshPage();
        Assert.assertEquals(mainPage.getPageNotFoundMessage(), """
                404
                Page not Found
                GO TO HOMEPAGE""", "Article keeps 'published' status");
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
