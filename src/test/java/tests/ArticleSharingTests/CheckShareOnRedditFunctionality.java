package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnRedditFunctionality extends BaseTest {
    @BeforeMethod(description = "Create and publish article")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test (description="WN-236 Share article on Redit")
    public void shareArticleOnReddit() throws InterruptedException {
        mainPage.selectTabInHeader("Betting Tips");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("reddit");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("reddit.com: Log in");
        redditPage.fillUsername();
        redditPage.fillPassword();
        redditPage.clickOnLogin();
//        redditPage.chooseCommunity();
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}

