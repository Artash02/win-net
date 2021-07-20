package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckMatchAnalysisTagIsVisibleForArticle extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Insert Content 3. Upload article image " +
            "4. Set winners.net in settings 5. Set 'Match-prediction' article tag 6. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleTag("match-predictions-and-analysis", "Match predictions");
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-7 : Check 'Match predictions' tag visible for created article in Analysis page")
    public void checkMatchPredictionsTagIsVisibleForArticle() {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        Assert.assertEquals(analysisPage.getSelectedTagForArticle(ArticleApiCalls.articleName), "Match predictions");
    }

    @AfterMethod(description = "Delete Created Article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}
