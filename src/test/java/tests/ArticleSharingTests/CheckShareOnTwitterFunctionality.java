package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnTwitterFunctionality extends BaseTest {

    @BeforeMethod(description = "create and publish")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();        
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test (description = "login in Twitter")
    public void shareArticleOnTwitter() throws InterruptedException {
        mainPage.selectTabInHeader("Betting Tips");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("twitter");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("Compose new Tweet / Twitter");
        twitterPage.fillEmail();
        twitterPage.fillPassword();
        twitterPage.clickOnLogin();
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
