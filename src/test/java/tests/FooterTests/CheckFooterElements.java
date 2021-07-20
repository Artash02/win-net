package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckFooterElements extends BaseTest {
    @Test(description = "WN-39 : (OK) Footer (WAF-13 & WAF-230)")
    public void checkAllFooterItems() {
        footerPage.footerItems();
        Assert.assertEquals(footerPage.getFooterBlockTitles(), new ArrayList<>(Arrays.asList("Winners.net",
                "Bookmakers", "Reviews", "Betting Tips", "Newsletter")), "The footer subblock titles is wrong");
        Assert.assertTrue(footerPage.checkAllFooterElements());
        softAssert.assertEquals(footerPage.getCopyrightText(), "© Copyright 2020 Winners.net",
                "The footer copyright text is wrong");
        softAssert.assertEquals(footerPage.getBottomPartText(), "Any information (both sports and entertainment) found on Winners.net is strictly for entertainment purposes. " +
                "We are not a sportsbook and do not take any wagers. We do not endorse illegal online gambling." +
                " Please check the online gambling regulations in your jurisdiction before placing any wagers with the betting sites advertised on Winners.net, as they do vary. " +
                "Winners.net does not target any individuals that are not of a legal age required by the online gambling regulations in your jurisdiction. " +
                "Using any of the information found at Winners.net to violate any law or statute is prohibited. " +
                "Winners.net is not supported by or linked to any professional, college or university league, " +
                "association, or team. Terms & Conditions apply to all bonus offers advertised. Please visit sportsbook operators for details.",
                "The footer bottom part text is wrong");
        softAssert.assertAll();
    }
}
