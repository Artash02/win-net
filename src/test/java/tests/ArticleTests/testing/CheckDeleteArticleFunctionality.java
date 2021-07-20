package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckDeleteArticleFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Insert Content 3. Upload article image" +
            "4. Set winners.net in settings 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-116 : Check Article Delete Functionality")
    public void checkDeleteArticleFunctionality() throws InterruptedException {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName + " value");
        articleApiCalls.deleteArticle();
        Thread.sleep(30000);
        mainPage.refreshPage();
        Assert.assertEquals(mainPage.getPageNotFoundMessage(), "404\n" +
                "Page not Found\n" +
                "GO TO HOMEPAGE");
    }

}
