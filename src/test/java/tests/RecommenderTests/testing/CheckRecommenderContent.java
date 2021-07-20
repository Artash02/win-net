package tests.RecommenderTests.testing;

import org.testng.Assert;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckRecommenderContent extends BaseTest {

    @Test(description = "(WN-40, WN-117) Check recommender pages content")
    public void checkBookmakerRecommenderTabsContent() throws Exception {
        mainPage.selectTabInHeader("Bookmaker Recommender");
        Assert.assertEquals(recommenderPage.getCountriesList().stream().sorted().toList()
                , backOfficeApiCalls.getCountriesFromApi("Countries").stream().sorted().toList()
                , "Lists do not match before clicking on 'Next' button!");
        Assert.assertEquals(recommenderPage.getQuestion(), "Based on your IP, it seems like you are connecting from Armenia");
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Which type of entertainment are you interested in?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Esports", "Sports", "Casino")));
        recommenderPage.selectCheckbox("Esports");
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Dota 2", "CS:GO", "LoL")));
        Assert.assertEquals(recommenderPage.getQuestion(), "Esports that you would like to bet on?");
        recommenderPage.clickOnOtherDropDown();
        Assert.assertEquals(recommenderPage.getOptionsFromOtherDropDown(), new ArrayList<>(Arrays.asList("League of Legends","PUBG"
                ,"Starcraft____1","ANNA","Cossacks3","Valorant","Mario")));
        recommenderPage.clickOnPreviousButton();
        recommenderPage.selectCheckbox("Esports");
        recommenderPage.selectCheckbox("Sports");
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Sports that you would like to bet on?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("Football", "Tennis", "Basketball")));
        recommenderPage.clickOnOtherDropDown();
        Assert.assertEquals(recommenderPage.getOptionsFromOtherDropDown(), new ArrayList<>(Arrays.asList("Valleyball","American football",
                "Baseball","Darts","Cycling","Cricket","Handball","Squash","Horse riding")));
        recommenderPage.clickOnPreviousButton();
        recommenderPage.selectCheckbox("Sports");
        recommenderPage.clickOnNextButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Which channel will you use to send and receive payment?");
        Assert.assertEquals(recommenderPage.getCheckBoxOptionNames(), new ArrayList<>(Arrays.asList("VISA"
                , "MasterCard", "Neteller")));
        recommenderPage.clickOnOtherDropDown();
        Assert.assertEquals(recommenderPage.getOptionsFromOtherDropDown(), new ArrayList<>(Arrays.asList("ARCA","Click2Pay","EMEX","Kiwi"
                ,"Visa Electron","Maestro","Paypal","SWIFT Transfers","Skrill","UCash","Yandex Money")));
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Which type of entertainment are you interested in?");
        recommenderPage.clickOnPreviousButton();
        Assert.assertEquals(recommenderPage.getQuestion(), "Based on your IP, it seems like you are connecting from Armenia");
        Assert.assertEquals(recommenderPage.getCountriesList().stream().sorted().toList(),
                backOfficeApiCalls.getCountriesFromApi("Countries").stream().sorted().toList()
                , "Lists do not match after clicking on 'Previous' button!");
        }
}
