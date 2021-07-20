package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckTagOpensInCurrentWindow extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert Tag Current Window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Tag Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();        
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-101 : Check Tag opens in current window")
    public void checkTagOpensInCurrentWindow() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName  + " value");
        articlePage.clickOnLink();
        Assert.assertEquals(mainPage.getWindowsCount(), 1);
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
