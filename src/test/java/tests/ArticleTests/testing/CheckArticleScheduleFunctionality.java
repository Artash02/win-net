package tests.ArticleTests.testing;

import api.ArticleApiCalls;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckArticleScheduleFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Add Simple content" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Simple Content");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.setArticleScheduleData(1);
    }

    @Test(description = "WN-129 : Check Article Schedule functionality")
    public void checkArticleScheduleFunctionality() throws InterruptedException {
        articleApiCalls.waitForScheduledDelay();
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article subtext"), "selenium-subtext-test12249");
        Assert.assertEquals(analysisPage.getSelectedInfoByArticleNameAndInfoName(ArticleApiCalls.articleName, "article info"),
                mainPage.getCurrentDate() + " • Zeus Πορτοκάλι");
    }

    @AfterMethod(description = "Delete created Article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}
