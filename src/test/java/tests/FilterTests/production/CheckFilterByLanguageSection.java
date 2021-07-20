package tests.FilterTests.production;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Collections;

public class CheckFilterByLanguageSection extends BaseTest {

    @Test(description = "WN-92 : (OK) Bookmakers filter by Language section")
    public void filterByLanguageSection() {
        filterPage.clickOnSearchIconsBySectionName("Language");
        filterPage.fillValueOnFilterSection("Language", "Portugese");
        filterPage.selectCheckbox("Language", "Portugese");
        softAssert.assertEquals(bookmakersTablePage.getBookmakersName(), new ArrayList<>(Collections.singletonList("info-test")));
        filterPage.fillValueOnFilterSection("Language", "Japanese");
        filterPage.selectCheckbox("Language", "Japanese");
        bookmakersTablePage.checkEmptyBookmakersTable();
        softAssert.assertTrue(bookmakersTablePage.checkEmptyBookmakersTable());
        softAssert.assertAll();
    }

}
