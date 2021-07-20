package tests.FooterTests;

import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckTermsOfServicePageContent extends BaseTest {
    @Test(description = "WN-18 : (OK) Terms of Service, WN-146 : (OK) Header/Footer URL")
    public void checkTermsOfService() throws Exception {
        footerPage.clickOnWinnersNetSubMenuPages("Terms of Service");
        softAssert.assertEquals(footerPage.getSubHeaderText(), "Terms of Service",
                "Invalid subheader text on the Terms of Service page");
        softAssert.assertEquals(footerPage.getTermsOfServicePageInfo(), new ArrayList<>(Arrays.asList("IMPORTANT NOTICE",
                "1. User Agreement", "2. Consideration", "3. Modifications", "4. Eligibility",
                "5. Communication And Information Practices", "6. Conduct", "7. User Content", "8. Intellectual Property Rights",
                "9. Third Party Websites", "10. Pre-Arbitration Claim Resolution", "11. Class Action Waiver", "12. Jury Waiver",
                "13. Warranty Disclaimers", "14. Limitation On Liability", "15. Miscellaneous")),
                "Invalid section name on the Terms of Service page");
        softAssert.assertEquals(mainPage.getCurrentUrl(), appConfig.getBaseUrl() + "/terms-of-service",
                "Invalid URL on the Terms of Service page");
        softAssert.assertEquals(footerPage.getContentByTitle("IMPORTANT NOTICE", "1. User Agreement"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "IMPORTANT NOTICE"),
                "There is invalid text on the Terms of Service page section 0");
        softAssert.assertEquals(footerPage.getContentByTitle("1. User Agreement", "2. Consideration"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "1. User Agreement"),
                "There is invalid text on the Terms of Service page section 1");
        softAssert.assertEquals(footerPage.getContentByTitle("2. Consideration", "3. Modifications"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "2. Consideration"),
                "There is invalid text on the Terms of Service page section 2");
        softAssert.assertEquals(footerPage.getContentByTitle("3. Modifications", "4. Eligibility"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "3. Modifications"),
                "There is invalid text on the Terms of Service page section 3");
        softAssert.assertEquals(footerPage.getContentByTitle("4. Eligibility", "5. Communication And Information Practices"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "4. Eligibility"),
                "There is invalid text on the Terms of Service page section 4");
        softAssert.assertEquals(footerPage.getContentByTitle("5. Communication And Information Practices", "6. Conduct"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "5. Communication And Information Practices"),
                "There is invalid text on the Terms of Service page section 5");
        softAssert.assertEquals(footerPage.getContentByTitle("6. Conduct", "7. User Content"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "6. Conduct"),
                "There is invalid text on the Terms of Service page section 6");
        softAssert.assertEquals(footerPage.getContentByTitle("7. User Content", "8. Intellectual Property Rights"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "7. User Content"),
                "There is invalid text on the Terms of Service page section 7");
        softAssert.assertEquals(footerPage.getContentByTitle("8. Intellectual Property Rights", "9. Third Party Websites"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "8. Intellectual Property Rights"),
                "There is invalid text on the Terms of Service page section 8");
        softAssert.assertEquals(footerPage.getContentByTitle("9. Third Party Websites", "10. Pre-Arbitration Claim Resolution"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "9. Third Party Websites"),
                "There is invalid text on the Terms of Service page section 9");
        softAssert.assertEquals(footerPage.getContentByTitle("10. Pre-Arbitration Claim Resolution", "11. Class Action Waiver"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "10. Pre-Arbitration Claim Resolution"),
                "There is invalid text on the Terms of Service page section 10");
        softAssert.assertEquals(footerPage.getContentByTitle("11. Class Action Waiver", "12. Jury Waiver"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "11. Class Action Waiver"),
                "There is invalid text on the Terms of Service page section 11");
        softAssert.assertEquals(footerPage.getContentByTitle("12. Jury Waiver", "13. Warranty Disclaimers"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "12. Jury Waiver"),
                "There is invalid text on the Terms of Service page section 12");
        softAssert.assertEquals(footerPage.getContentByTitle("13. Warranty Disclaimers", "14. Limitation On Liability"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "13. Warranty Disclaimers"),
                "There is invalid text on the Terms of Service page section 13");
        softAssert.assertEquals(footerPage.getContentByTitle("14. Limitation On Liability", "15. Miscellaneous"),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "14. Limitation On Liability"),
                "There is invalid text on the Terms of Service page section 14");
        softAssert.assertEquals(footerPage.getContentByTitle("15. Miscellaneous", null),
                excel.readDataByColumn("src/test/resources/PrivacyPolicy.xls", "Terms of Service", "15. Miscellaneous"),
                "There is invalid text on the Terms of Service page section 15");
        softAssert.assertAll();
    }
}
