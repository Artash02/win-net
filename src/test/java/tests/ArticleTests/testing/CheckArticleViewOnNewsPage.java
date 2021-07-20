package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckArticleViewOnNewsPage extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void createArticle() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleTag("sports", "Football");
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-98 : (WAF-302) Check article view on news page")
    public void checkArticleViewOnNewsPage() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article tag"), "Football");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article subtext"), "selenium-subtext-test12249");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article info"), "Football " +
                mainPage.getCurrentDate() + " • Zeus Πορτοκάλι");
    }

    @AfterMethod(description = "Delete created article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}
