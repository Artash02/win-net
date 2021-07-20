package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Log;

public class CheckArticleImageSize extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-166 : (WAF-298) Article image should be original image (and its scaled versions).")
    public void checkArticleImageSize() throws InterruptedException {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        float analysisPageImageSize = analysisPage.getImageSize();
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName + " value");
        float articlePageImageSize = articlePage.getImageSizeInArticlePage();
        mainPage.openUrl(articlePage.getImageUrl());
        float newPageImageSize = articlePage.getImageSizeOnNewPage();
        Log.warn(String.valueOf(analysisPageImageSize));
        Log.warn(String.valueOf(articlePageImageSize));
        Log.warn(String.valueOf(newPageImageSize));
        Assert.assertEquals(analysisPageImageSize, articlePageImageSize);
        Assert.assertEquals(articlePageImageSize, newPageImageSize);
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
