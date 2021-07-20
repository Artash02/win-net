package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleUrlWIthIncorrectSlug extends BaseTest {
    @BeforeMethod(description = "1. Create article, 2. Add simple content , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticleWithNameWhichContainsBackSlash();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-145 Check article Url with incorrect slug")
    public void checkArticleUrlWithIncorrectSlug() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleNameWithBackSlash);
        Assert.assertNotEquals(mainPage.getPageUrl(), appConfig.getBaseUrl() + "/analysis-and-predictions/" + ArticleApiCalls.articleId + "/" + ArticleApiCalls.articleNameWithBackSlash);
    }

    @AfterMethod(description = "Delete created Article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}

