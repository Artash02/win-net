//package tests.ArticleTests.testing;
//
//import api.ArticleApiCalls;
//import tests.base.BaseTest;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class CheckOpenArticleInaNewTabFunctionality extends BaseTest {
//
//    @BeforeMethod(description = "Create article")
//    public void createArticle() throws Exception {
//        articleApiCalls.createArticle();
//        articleApiCalls.addArticleContent("Simple Content");
//        articleApiCalls.uploadArticleImage();
//        articleApiCalls.setWinnersNetInSettings();
//        articleApiCalls.setArticleStatusPublishNow();
//    }
//
//    @Test(description = "Check open article in a new tab functionality")
//    public void checkOpenArticleInaNewTabFunctionality() throws InterruptedException {
//        mainPage.selectTabInHeader("Betting Tips");
//        analysisPage.openArticleInaNewTab(ArticleApiCalls.articleName);
//        mainPage.handleWindowByTitle(ArticleApiCalls.articleName);
//    }
//
//    @AfterTest(description = "Delete created article", alwaysRun = true)
//    public void deleteCreatedArticle() throws Exception {
//        articleApiCalls.deleteArticle();
//    }
//}
