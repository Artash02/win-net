package tests.CleanURLsTests;

import api.ArticleApiCalls;
import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckCountryKeptOnArticlePage extends BaseTest {
    @BeforeMethod(description = "1. Create bookmaker that is restricted for Armenia, 2. Create article")
    public void beforeMethod() throws Exception {
        bookmakerApiCalls.createBookmaker();
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmakerId);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB_RESTRICTED_COUNTRIES);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmakerId
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmakerId,
                bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmakerId,"published");

        articleApiCalls.createArticle();
        articleApiCalls.addArticleContent("Article Content Headers");
        articleApiCalls.uploadArticleImage();
        articleApiCalls.setWinnersNetInSettings();
        articleApiCalls.changeArticleStatusTo("published");
    }

    // Automation bug???
    @Test(description = "WN-242 : (OK) Country kept on article's page")
    public void countryKeepInArticlePage() throws InterruptedException {
        filterPage.selectCountryInHeader("Albania");
        mainPage.waitToProgressBarIsNotDisplayed();
        mainPage.selectTabInHeader("Betting Tips");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"));
        softAssert.assertEquals(analysisPage.getAnalysisSubheaderText(), "Betting Tips",
                "The betting tips page header is incorrect");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/betting-tips?countries=" +
                bookmakerApiCalls.getCountryIdByName("Albania"),
                "The betting tips page URL is incorrect");
        analysisPage.selectArticleByNumber(0);
        mainPage.waitToTitleContains(ArticleApiCalls.articleName);
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        Assert.assertEquals(filterPage.countryIs(), "Albania",
                "The country isn't Albania after going from the article page");
        filterPage.searchBookmakerName(BookmakerApiCalls.bookmakerName);
        Assert.assertEquals(filterPage.getEmptyListText(), "No bookmakers available for your region or filters set",
                "There is bookmaker on the bookmaker list");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmakerId);
        articleApiCalls.deleteArticle();
    }
}
