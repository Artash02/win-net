package tests.CleanURLsTests;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class CheckUnexpectedRefresh extends BaseTest {
    @Test(description = "WN-245 : (NOK) Unexpected refresh")
    public void unexpectedRefresh() {
        filterPage.selectCountryInHeader("Andorra");
        Assert.assertEquals(filterPage.countryIs(), "Andorra",
                "Current country is not Algeria");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/?countries=" +
                bookmakerApiCalls.getCountryIdByName("Andorra") + "&sort=-reviews.overall.rating",
                "The homepage URL is wrong for Algeria");
        footerPage.clickOnWinnersNetSubMenuPages("About Us");
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("About Us"));
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/about-us",
                "The about us page URL is wrong");
        mainPage.refreshPage();
        //Appears bug after refreshing on any page that url doesn't contain country id
        mainPage.clickOnHomepageIcon();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        Assert.assertEquals(filterPage.countryIs(),"Andorra",
                "The country isn't kept after refreshing on about us page");
        softAssert.assertAll();
    }
}
