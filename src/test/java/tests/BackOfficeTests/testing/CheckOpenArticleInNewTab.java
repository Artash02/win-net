package tests.BackOfficeTests.testing;

import api.ArticleApiCalls;
import org.testng.annotations.AfterMethod;
import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Log;

public class CheckOpenArticleInNewTab extends BaseTest {

    @BeforeMethod(description = "1. Create article, 2. Insert link in content which opens in current window , " +
            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();        
        articleApiCalls.changeArticleStatusTo("published");
    }

    @Test(description = "WN-161: Check Open Article In New Tab")
    public void checkOpenArticleInNewTab() {
        mainPage.openUrl(appConfig.getBackOfficeUrl());
        backOfficePage.fillEmail(appConfig.getBackofficeUsername());
        backOfficePage.fillPassword(appConfig.getBackOfficePassword());
        backOfficePage.clickOnLoginButton();
        mainPage.openUrl("https://backoffice.priotix.xyz/news");
        backOfficePage.fillValueInSearchField(ArticleApiCalls.articleName);
        backOfficePage.clickOnArticle(ArticleApiCalls.articleName);
        Assert.assertTrue(mainPage.urlContains(String.valueOf(ArticleApiCalls.articleId)), "url not contains " + ArticleApiCalls.articleId + " value");
        backOfficePage.clickOnOpenArticleInNewTabButton();
        backOfficePage.navigateSecondWindow();
        mainPage.openUrl(appConfig.getBaseUrl() + "/betting-tips/" + ArticleApiCalls.articleId  + "/" + ArticleApiCalls.articleName);
        Log.warn(mainPage.getTitle());
        Assert.assertEquals(mainPage.getTitle(), ArticleApiCalls.articleName + " - News - Winners.net");
    }

    @AfterMethod(description = "Delete created article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}
