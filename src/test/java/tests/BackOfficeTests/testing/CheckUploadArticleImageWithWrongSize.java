package tests.BackOfficeTests.testing;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

//TO DO
public class CheckUploadArticleImageWithWrongSize extends BaseTest {

    @BeforeMethod(description = "1. Create article 2. Add article content for created article")
    public void beforeMethod() throws Exception {
        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
    }

    @Test(description = "Check upload article image with wrong size")
    public void checkUploadArticleImageWithWrongFormat() throws Exception {
        Assert.assertEquals(articleApiCalls.uploadArticleImageWithWrongSize(), 200);
    }

    @AfterMethod(description = "Delete created article", alwaysRun = true)
    public void deleteCreatedArticle() {
        articleApiCalls.deleteArticle();
    }
}
