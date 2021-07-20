package tests.ArticleSharingTests;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckShareOnFacebookFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Set Article Content" +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Insert Link Current Window");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();        
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test (description ="WN-234: Ckeck Facebook functionality")
    public void shareArticleOnFacebook() throws InterruptedException {
        mainPage.selectTabInHeader("Betting Tips");
        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
        analysisPage.selectArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName  + " value");
        Log.warn(ArticleApiCalls.articleName);
        articlePage.clickOnShareButton("facebook");
        Assert.assertEquals(mainPage.getWindowsCount(), 2);
        mainPage.handleWindowByTitle("Facebook");
        facebookPage.fillEmail();
        facebookPage.fillPassword();
        facebookPage.clickOnLogin();
        facebookPage.clickOnPublishArticleButton();
        mainPage.handleWindowByTitle("Esports betting sites | Customize your need and find the best bookmaker");
    }

    @AfterMethod(description = "delete created article", alwaysRun = true)
    public void afterTest() {
        articleApiCalls.deleteArticle();
    }
}
