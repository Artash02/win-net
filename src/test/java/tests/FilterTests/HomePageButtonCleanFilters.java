package tests.FilterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class HomePageButtonCleanFilters extends BaseTest {

    @Test(description = "(OK) Home page button clean 'Sports Covered' section filters (WAF-412)")
    public void checkHomePageButtonCleanSportsCoveredSectionFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Sports Covered");
        filterPage.fillValueOnFilterSection("Sports Covered", "American football");
        filterPage.selectCheckbox("Sports Covered", "American football");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Sports Covered");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Sports Covered"), new ArrayList<>(Collections.emptyList()));
    }

    @Test(description = "(OK) Home page button clean 'Esports Covered' section filters (WAF-412)")
    public void checkHomePageButtonCleanEsportsCoveredSectionFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Esports Covered");
        filterPage.fillValueOnFilterSection("Esports Covered", "LOL");
        filterPage.selectCheckbox("Esports Covered", "LOL");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Esports Covered");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Esports Covered"), new ArrayList<>(Collections.emptyList()));
    }

    @Test(description = "(OK) Home page button clean 'Other Products' section filters (WAF-412)")
    public void checkHomePageButtonCleanOtherProductsFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Other Products");
        filterPage.fillValueOnFilterSection("Other Products", "Pool Betting");
        filterPage.selectCheckbox("Other Products", "Pool Betting");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Other Products");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Other Products"), new ArrayList<>(Collections.emptyList()));
    }

    @Test(description = "(OK) Home page button clean 'Payment Accepted' section filters (WAF-412)")
    public void checkHomePageButtonCleanPaymentAcceptedFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Payment Accepted");
        filterPage.fillValueOnFilterSection("Payment Accepted", "VISA");
        filterPage.selectCheckbox("Payment Accepted", "VISA");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Payment Accepted");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Payment Accepted"), new ArrayList<>(Collections.emptyList()));
    }

    @Test(description = "(OK) Home page button clean 'Cashout Available' section filters (WAF-412)")
    public void checkHomePageButtonCleanCashoutAvailableFiltersFunctionality() throws Exception {
        filterPage.clickOnExpandIconsBySectionName("Cashout Available");
        filterPage.selectValueForCashoutAvailableSection("No");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Cashout Available");
        Assert.assertEquals(filterPage.getCheckedCashoutAvailableSectionItem(),"No Matter" );
    }

    @Test(description = "(OK) Home page button clean 'Currency Accepted' section filters (WAF-412)")
    public void checkHomePageButtonCleanCurrencyAcceptedFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Currency Accepted");
        filterPage.fillValueOnFilterSection("Currency Accepted", "XAU");
        filterPage.selectCheckbox("Currency Accepted", "XAU");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Currency Accepted");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Currency Accepted"), new ArrayList<>(Collections.emptyList()));
    }

    @Test(description = "(OK) Home page button clean 'Language' section filters (WAF-412)")
    public void checkHomePageButtonCleanLanguageFiltersFunctionality() throws Exception {
        filterPage.clickOnSearchIconsBySectionName("Language");
        filterPage.fillValueOnFilterSection("Language", "Spanish");
        filterPage.selectCheckbox("Language", "Spanish");
        filterPage.clickOnWinnersLogo();
        filterPage.clickOnExpandIconsBySectionName("Language");
        Assert.assertEquals(filterPage.getCheckedFilterItemsBySectionName("Language"), new ArrayList<>(Collections.emptyList()));
    }


}
