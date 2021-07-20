package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class RecommenderPage extends MainPage {

    @FindBy(css = ".ReactDropdownSelect .react-dropdown-select-content")
    private WebElement dropDownElement;

    @FindBy(css = ".ReactDropdownSelect .search-items")
    private WebElement dropDownSearchItemElement;

    @FindBy(css = ".ReactDropdownSelect .Search input")
    private WebElement fillCountryFilterElement;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButtonElement;

    @FindBy(className = "view-btn")
    public WebElement viewButtonElement;

    @FindBy(xpath = "//button[text()='Previous']")
    private WebElement previousButtonElement;

    @FindBy(css = ".FilterItems .Checkbox")
    private List<WebElement> checkBoxElements;

    @FindBy(css = ".FilterItems .OtherOptions")
    private WebElement othersElement;

    @FindBy(className = "RecommenderFooter")
    private WebElement recommenderFooterElement;

    @FindBy(className = "tab")
    private WebElement tabElement;

    @FindBy(className = "FilterItems")
    private WebElement checkBoxElement;

    @FindBy(css = ".search-items .Checkbox")
    private List<WebElement> othersCheckBoxElements;


    public RecommenderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectCountryInRecommenderPage(String value) {
        scrollIntoElement(dropDownElement);
        clickOnElement(dropDownElement);
        fillText(fillCountryFilterElement, value);
        List<WebElement> searchItemElements = dropDownSearchItemElement.findElements(By.className("search-item"));
        clickOnElement(findElementByText(searchItemElements, value));
        waitToElementIsVisible(nextButtonElement);
    }


    public String getQuestion() {
        WebElement questionElement = webDriver.findElement(By.className("tab")).findElements(By.tagName("div")).get(0);
        return questionElement.getText();
    }

    public ArrayList getCheckBoxOptionNames() throws InterruptedException {
        ArrayList options = new ArrayList<>();
        Thread.sleep(2000);
        List<WebElement> elements = checkBoxElement.findElements(By.className("Checkbox"));
        for (WebElement element : elements) {
            options.add(element.getText());
        }
        Log.warn(String.valueOf(options));
        return options;
    }

    public void clickOnNextButton() {
        clickOnElement(nextButtonElement);
    }

    public void clickOnViewButton() {
        //scrollIntoElement(viewButtonElement);
        clickOnElement(viewButtonElement);
    }

    public void clickOnPreviousButton() {
        clickOnElement(previousButtonElement);
    }

    public void selectCheckbox(String checkBoxName) {
        clickOnElement(findElementByText(checkBoxElements,checkBoxName));
    }

    public void selectOthers(String othersName) {
        clickOnElement(othersElement);
        clickOnElement(findElementByText(othersCheckBoxElements, othersName));
        clickOnElement(othersElement);
    }

    public ArrayList getCountriesList() {
        ArrayList countryItems = new ArrayList<>();
        waitToElementIsVisible(dropDownElement);
        scrollIntoElement(dropDownElement);
        clickOnElement(dropDownElement);
        List<WebElement> searchItemElements = webDriver.findElements(By.className("search-item"));
        for (WebElement searchItemElement : searchItemElements) {
            countryItems.add(searchItemElement.getText());
        }
        Log.warn(String.valueOf(countryItems));
        waitToElementIsVisible(tabElement);
        clickOnElement(tabElement);
        return countryItems;
    }

    public ArrayList getOptionsFromOtherDropDown() {
        ArrayList options = new ArrayList<>();
        List<WebElement> searchItemElements = dropDownSearchItemElement.findElements(By.className("Checkbox"));
        for (WebElement searchItemElement : searchItemElements) {
            options.add(searchItemElement.getText());
        }
        return options;
    }

    public int getBookmakersCount() throws InterruptedException {

        waitToProgressBarIsNotDisplayed();
        waitToElementIsVisible(recommenderFooterElement);
        int bookmakersCount = Integer.parseInt(recommenderFooterElement.getText().split("Bookmakers")[0].trim());
        System.out.println(bookmakersCount);
        return bookmakersCount;
    }
    public void clickOnOtherDropDown(){
        clickOnElement(dropDownElement);
    }
}
