package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MainPage extends BasePage {
    @FindBy(id = "MainContent")
    private WebElement mainContentElement;

    @FindBy(className = "NavigationItems")
    private WebElement NavigationItemsElement;

    @FindBy(className = "esport-menu-items")
    private WebElement EsportSitesElement;

    @FindBy(className = "esport-dropdown-button")
    private WebElement bestEsportBettingsSitesIconElement;

    @FindBy(className = "esport-dropdown-button-text")
    private WebElement esportDropDownButtonTextElement;

    @FindBy(css = ".navigation-main-logo .WinnersLogo")
    private WebElement homepageIconElement;

    @FindBy(className = "bookmaker-item-select")
    private List<WebElement> addBookmakerToComparisonElements;

    @FindBy(className = "legend-item")
    private List<WebElement> comparisonBookmakerElements;

    @FindBy(className = "RadarChart")
    private WebElement chartElement;

    @FindBy(className = "country-setting-title")
    private WebElement subHeaderTextElement;

    @FindBy(className = "country-setting-description")
    private WebElement pageDescriptionTextElement;

    @FindBy(className = "age-restriction")
    private WebElement ageRestrictionElement;

    @FindBy(className = "seo-title")
    public WebElement bestBettingSitesTextElement;


    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getIpBasedCountry() throws IOException {
        Scanner s = new Scanner(new java.net.URL("https://api.ipdata.co/country_name?api-key=9dca87cc11bd9cf9d71de3e90e2a8d968759f17ec7d77c63a112003e").openStream(), StandardCharsets.UTF_8).useDelimiter("\\A");
        return s.next();
    }

    public void clickOnHomepageIcon() {
        homepageIconElement.click();
    }

    //TODO V3
    public void openUrl(String url) {
        webDriver.navigate().to(url);
    }

    public int getComparisonChartBookmakersCount() {
        return comparisonBookmakerElements.size();
    }

    public void addBookmakerToComparisonByPosition(int bookmakerPosition) {
        waitToElementIsVisible(chartElement);
        scrollIntoElement(chartElement);
        addBookmakerToComparisonElements.get(bookmakerPosition - 1).click();
    }

    public void selectTabInHeader(String tabName) {
        List<WebElement> navigationTabElements = NavigationItemsElement.findElements(By.className("navigation-item"));
        clickOnElement(findElementByText(navigationTabElements, tabName));
    }

    public void selectBestEsportsBettingSites(String value) {
        scrollIntoElement(bestEsportBettingsSitesIconElement);
        clickOnElement(bestEsportBettingsSitesIconElement);
        waitToElementIsVisible(webDriver.findElement(By.className("DropdownOpen")));
        List<WebElement> esportGameElements = EsportSitesElement.findElements(By.tagName("a"));
        WebElement divElement = findElementByText(esportGameElements, value).findElement(By.tagName("div"));
        clickOnElement(divElement);
    }

    public ArrayList getBestEsportsBettingSitesMenuItems() {
        ArrayList esportsItems = new ArrayList<>();
        waitToElementIsVisible(bestEsportBettingsSitesIconElement);
        clickOnElement(bestEsportBettingsSitesIconElement);
        waitToElementIsVisible(EsportSitesElement);
        List<WebElement> esportsMenuItemElements = EsportSitesElement.findElements(By.className("esport-menu-item"));
        for (WebElement esportsMenuItemElement : esportsMenuItemElements) {
            esportsItems.add(esportsMenuItemElement.getText());
        }
        return esportsItems;
    }

    public String getSelectedEsportMenuItem() {
        return esportDropDownButtonTextElement.getText();
    }

    public String getPageSubHeaderText() {
        return subHeaderTextElement.getText();
    }

    public String getPageDescriptionText() {
        return pageDescriptionTextElement.getText();
    }

    public String getPageNotFoundMessage() {
        Log.warn(mainContentElement.getText());
        return mainContentElement.getText().replace("/n", "");
    }

    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MMM dd, yyyy");
        return simpleFormat.format(cal.getTime());
    }

    public String getPageUrl() {
        waitToProgressBarIsNotDisplayed();
        return webDriver.getCurrentUrl();
    }

    public String getRobotsTxt() {
        return webDriver.findElement(By.tagName("pre")).getText();
    }

    public String getAgeRestriction() {
        waitToElementIsVisible(ageRestrictionElement);
        scrollIntoElement(ageRestrictionElement);
        return ageRestrictionElement.getText();
    }

    public void waitToProgressBarIsNotDisplayed() {
        waitToElementIsNotDisplayed(By.className("Loader"));
    }

    public void switchToNewWindow() {
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle);
        }
    }
}
