package tests.FilterTests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckWildeCharactersBehaviourInCountrySelection extends BaseTest {

    @Test(description = "WN-303 Check wilde characters behaviour in Country-selector autocomplete")
    public void checkWildeCharactersBehaviourInCountrySelection() throws InterruptedException {

        String[] listOfWildeCharacters = new String[]{".", ",", "[", "]", "{", "}", ":", ";", "/", "+", "$", "%", "&", "*",
                "#", "@", "!", "'", "(", ")", "+", "<", ">", "^", "~", "`"};
        String[] listOfAToZCharacters = new String[]{"e", " "};

        mainPage.selectTabInHeader("Bookmaker Comparison");
        softAssert.assertTrue(filterPage.returnSizeOfListResultsOfLanguageFilterBehaviour(listOfWildeCharacters).equals(0)
                , "Error on list containing wilde characters");
        mainPage.refreshPage();
        mainPage.waitToProgressBarIsNotDisplayed();
        softAssert.assertFalse(filterPage.returnSizeOfListResultsOfLanguageFilterBehaviour(listOfAToZCharacters).equals(0)
                , "Error on list containing A-Z characters");
        softAssert.assertAll();
    }
}

