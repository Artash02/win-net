package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckArticlePageFromFooter extends BaseTest {
    @Test(description = "WN-278 : (OK) Article page")
    public void checkAnalysisPage() {
        footerPage.clickOnFooterBettingTips();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Betting Tips"));
        Assert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/betting-tips",
                "Betting tips page url is incorrect");
        Assert.assertEquals(footerPage.getNewsPageSubHeader(), "Betting Tips",
                "The articles page header is incorrect");
    }
}
