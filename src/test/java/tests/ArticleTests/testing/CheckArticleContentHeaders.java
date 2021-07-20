package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;


public class CheckArticleContentHeaders extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-157 : Checks Article-Content Headers (WAF-297) ")
    public void checkArticleContentHeaders() {

        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName + " value");
        HashMap<String, String> contentHeaders = articlePage.getHeading();
        Assert.assertEquals("Heading2", contentHeaders.get("h2"));
        Assert.assertEquals("Heading3", contentHeaders.get("h3"));
        Assert.assertEquals("Heading4", contentHeaders.get("h4"));
        Assert.assertEquals("Heading5", contentHeaders.get("h5"));
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
