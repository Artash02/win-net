//package tests.ArticleTests.testing;
//
//import api.ArticleApiCalls;
//import org.testng.annotations.AfterMethod;
//import tests.base.BaseTest;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class CheckArticleSourceCodeVideoFunctionality extends BaseTest {
//
//    @BeforeMethod(description = "1. Create article, 2. Insert Source Code Video With Embed Code, " +
//            "3. Upload article image, 4. Set winners.net in settings, 5. Set article status publish now")
//    public void beforeMethod() throws Exception {
//        articleApiCalls.createArticle();
//        articleApiCalls.addArticleContent("Insert Source Code Video Using Youtube Embed Code");
//        articleApiCalls.uploadArticleImage();
//        articleApiCalls.setWinnersNetInSettings();
//        articleApiCalls.changeArticleStatusTo("published");
//    }
//
//    @Test(description = "WN-127 : Check open twitch functionality")
//    public void checkArticleSourceCodeVideoFunctionality() throws Exception {
//        mainPage.selectTabInHeader("Betting Tips");
//        Assert.assertTrue(mainPage.urlContains("betting-tips"), "url not contains 'betting-tips' value");
//        analysisPage.selectArticle(ArticleApiCalls.articleName);
//        Assert.assertTrue(mainPage.urlContains(ArticleApiCalls.articleName), "url not contains" + ArticleApiCalls.articleName + " value");
//        articlePage.switchToIframe();
//        Assert.assertTrue(mainPage.getPageSource().contains("https://www.youtube.com"));
//        articleApiCalls.addArticleContent("Insert Reddit Embed code");
//        mainPage.refreshPage();
//        Assert.assertEquals(redditPage.getBlockQuoteText(), "Wettbewerb: Karnevals-, Fastnachts-, Fassenachts-, Fasnachts-, Fasnets-, Faschings-, Fastabends-, Fastelovends- oder Fasteleersgebäck from r/Kochen");
//        Assert.assertEquals(mainPage.getPageUrl(), appConfig.getBaseUrl() + "/betting-tips/"
//                + ArticleApiCalls.articleId + "/" + ArticleApiCalls.articleName);
//    }
//
//    @AfterMethod(description = "Delete created article", alwaysRun = true)
//    public void afterTest() {
//        articleApiCalls.deleteArticle();
//    }
//}
