package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckTagOpensInNewWindow extends BaseTest {


    @BeforeMethod(description = "1. Create article, 2. Insert tag in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Tag New Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();        
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-100 : (OK) Article-Content-Insert-Tag-New-Window (WAF-302) Check tag opens in new window")
    public void checkTagOpensInNewWindow() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName  + " value");
        articlePage.clickOnLink();
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("Login");
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }

}
