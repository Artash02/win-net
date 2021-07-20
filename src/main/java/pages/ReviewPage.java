package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

public class ReviewPage extends MainPage {

    @FindBy(className = "review-nav-title")
    private List<WebElement> reviewNavTitleElement;

    @FindBy(className = "review-indicator-title")
    private List<WebElement> reviewIndicatorTitleElement;

    @FindBy(className = "ButtonCompare")
    private WebElement compareButtonElement;

    @FindBy(className = "responsibleGaming")
    private WebElement responsibleGamingSectionElement;

    @FindBy(className = "review-compare-button")
    private WebElement bonusComparisonButtonElement;

    @FindBy(xpath = "//img[@alt='bookmaker-logo']")
    private WebElement bookmakerLogoElement;

    @FindBy(css = ".additionalInfo .countries .tags-wrapper .review-tag")
    private List<WebElement> excludedCountriesElements;

    @FindBy(css = ".additionalInfo .currencies .tags-wrapper .review-tag")
    private List<WebElement> currenciesElements;

    @FindBy(className = "ShowMore")
    private List<WebElement> showMoreButtonAdditionalInfoElement;

    @FindBy(className = "expand-button")
    private WebElement readMoreButtonElement;

    @FindBy(xpath = "//button[text()='Show Less']")
    private WebElement showLessButtonElement;

    @FindBy(className = "overview-title")
    private WebElement overviewTitleElement;

    @FindBy(className = "website-url")
    private WebElement webSiteUrlElement;

    @FindBy(className = "icon-wrapper")
    private WebElement acceptPlayersElement;

    @FindBy(className = "claim-bonus")
    private WebElement claimBonusButtonElement;

    @FindBy(className = "overview-content")
    private WebElement overviewElement;

    @FindBy(id = "bonus")
    private WebElement bonusReviewElement;

    @FindBy(id = "products")
    private WebElement productsElement;

    @FindBy(id = "responsibleGaming")
    private WebElement responsibleGamingElement;

    @FindBy(id = "depositAndWithdrawal")
    private WebElement depositAndWithdrawalElement;

    @FindBy(id = "onboarding")
    private WebElement onBoardingElement;

    @FindBy(id = "customerServices")
    private WebElement customerServicesElement;

    @FindBy(id = "utility")
    private WebElement utilityElement;

    @FindBy(id = "additionalInfo")
    private WebElement additionalInfoElement;

    @FindBy(className = "ReviewNavBarIndicator")
    private WebElement reviewNavBarIndicatorElement;

    @FindBy(className = "bookmaker-name")
    private WebElement bookmakerNameButtonElement;

    @FindBy(className = "title")
    private WebElement websiteButtonElement;

    @FindBy(className = "ButtonCompare")
    private WebElement compareWithOthersButtonElement;

    public ReviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnNavBarItem(String value) {
        assert urlContains("review");
        Log.warn(String.valueOf(reviewNavTitleElement.size()));
        clickOnElement(findElementByText(reviewNavTitleElement, value));
    }


    public void clickOnNavBarItemNew(String value) {
        waitToProgressBarIsNotDisplayed();
        List<WebElement> el = webDriver.findElements(By.className("ReviewNavBarItem"));
        for (WebElement i : el) {
            if (i.getText().equals(value)) {
                i.click();
                break;
            }
        }
    }

    public void clickOnCompareWithOthersButton() {
        waitToElementIsVisible(responsibleGamingSectionElement);
        scrollIntoElement(responsibleGamingSectionElement);
        clickOnElement(compareButtonElement);
    }

    public void clickOnReadMoreButton() {
        waitToProgressBarIsNotDisplayed();
        clickOnElement(readMoreButtonElement);
    }

    public void clickOnShowLessButton() {
        clickOnElement(showLessButtonElement);
    }

    public void clickOnWebSiteUrl() {
        WebElement el = webDriver.findElement(By.className("website-url"));
        try {
            el.click();
        } finally {return; }
    }

    public void clickOnClaimBonusButton() {
        clickOnElement(claimBonusButtonElement);
    }

    public boolean readMoreButtonIsVisible() {
        waitToElementIsVisible(readMoreButtonElement);
        return true;
    }

    public boolean showLessButtonIsVisible() {
        waitToElementIsVisible(showLessButtonElement);
        return true;
    }

    public void clickOnBonusComparisonButton() {
        clickOnElement(bonusComparisonButtonElement);
    }

    public String getAcceptPlayers() {
        waitToElementIsVisible(acceptPlayersElement);
        return acceptPlayersElement.getText();
    }

    public String getBookmakerReviewPageTitle() {
        waitToElementIsVisible(overviewTitleElement);
        return overviewTitleElement.getText();
    }

    public String getOverviewContent() {
        waitToElementIsVisible(overviewElement);
        return overviewElement.getText();
    }

    public ArrayList getExcludedCountries() {
        ArrayList excludedCountries = new ArrayList<>();
        for (WebElement excludedCountriesElement : excludedCountriesElements) {
            excludedCountries.add(excludedCountriesElement.getText());
        }
        Log.warn(String.valueOf(excludedCountries));
        return excludedCountries;
    }

    public ArrayList getCurrencies() {
        ArrayList excludedCountries = new ArrayList<>();
        for (WebElement excludedCountriesElement : currenciesElements) {
            excludedCountries.add(excludedCountriesElement.getText());
        }
        Log.warn(String.valueOf(excludedCountries));
        return excludedCountries;
    }

    public float getBookmakerLogoSize() {
        waitToElementIsVisible(bookmakerLogoElement);
        String imageSrc = bookmakerLogoElement.getAttribute("src");
        assert imageSrc.contains("progressive");
        String imageWidth = imageSrc.split("/h/")[0].split("w/")[1];
        String imageHeight = imageSrc.split("/h/")[1].split("/format")[0];
        int width = Integer.parseInt(imageWidth);
        int height = Integer.parseInt(imageHeight);
        return (float) width / (float) height;
    }

    public String getReviewContentBetweenTitles(String startTitle, String endTitle) throws InterruptedException {
        String xpath;
        Thread.sleep(3000);
        if (endTitle == null) {
            xpath = "//h4[text() = " + "'" + startTitle + "'" + "]/following-sibling::*";
        } else {
            xpath = "//h4[text()=" + "'" + startTitle + "'" + "]/following-sibling::*[following::h4[text()=" + "'" + endTitle + "']]";
        }
        WebElement spanElement = webDriver.findElement(By.xpath(xpath));
        waitToElementIsVisible(spanElement);
        Log.warn(spanElement.getText());
        return spanElement.getText();
    }

    public String getContentBySectionName(String sectionName) {
        String content;
        WebElement webElement;
        switch (sectionName) {
            case "Basic Info" -> webElement = overviewElement;
            case "Bonuses" -> webElement = bonusReviewElement;
            case "Sport / Game Coverage" -> webElement = productsElement;
            case "Responsible Gaming" -> webElement = responsibleGamingElement;
            case "Deposit & Withdrawal" -> webElement = depositAndWithdrawalElement;
            case "Onboarding Process" -> webElement = onBoardingElement;
            case "Customer Service" -> webElement = customerServicesElement;
            case "Utility Features" -> webElement = utilityElement;
            case "Additional Info" -> webElement = additionalInfoElement;
            default -> throw new IllegalArgumentException("Review Name is incorrect");
        }
        waitToElementIsVisible(webElement);
        scrollIntoElement(webElement);
        content = webElement.getText();
        Log.warn(content);
        return content;
    }

    public int getRatingBySectionName(String sectionName) {
        WebElement webElement;
        switch (sectionName) {
            case "Bonuses" -> webElement = bonusReviewElement;
            case "Sport / Game Coverage" -> webElement = productsElement;
            case "Responsible Gaming" -> webElement = responsibleGamingElement;
            case "Deposit & Withdrawal" -> webElement = depositAndWithdrawalElement;
            case "Onboarding Process" -> webElement = onBoardingElement;
            case "Customer Service" -> webElement = customerServicesElement;
            case "Utility Features" -> webElement = utilityElement;
            default -> throw new IllegalArgumentException("Review Name is incorrect");
        }
        String ratingElement = webElement.findElement(By.cssSelector(".ProgressIndicator")).
                findElement(By.tagName("progress")).getAttribute("value");
        int rating = Integer.parseInt(ratingElement);
        System.out.println(rating);
        return rating;
    }

    public String getRankedOut() {
        waitToProgressBarIsNotDisplayed();
        waitToElementIsVisible(reviewNavBarIndicatorElement);
        return reviewNavBarIndicatorElement.getText();
    }

    public ArrayList getCaptionOfShowMoreButton (){
        ArrayList result=new ArrayList();
        List<WebElement> el=webDriver.findElements(By.className("ShowMore"));
        for (int i=0;i<el.size();++i){
          result.add(el.get(i).getAttribute("outerText"));
        }
        return result;
    }

    public void clickOnShowMoreButtonByFieldNameTwo(String fieldName) throws InterruptedException {

        try {
            switch (fieldName) {
                case "Excluded countries" -> clickOnElement(showMoreButtonAdditionalInfoElement.get(0));
                case "Currency Accepted" -> clickOnElement(showMoreButtonAdditionalInfoElement.get(1));
                default -> throw new IllegalArgumentException("Review Name is incorrect");
            }
        } finally { return; }
    }

    public void clickOnShowMoreButtonByFieldName(String fieldName) throws InterruptedException {
        Thread.sleep(300);
        switch (fieldName) {
            case "Excluded countries" -> clickOnElement(showMoreButtonAdditionalInfoElement.get(0));
            case "Currency Accepted" -> clickOnElement(showMoreButtonAdditionalInfoElement.get(1));
            default -> throw new IllegalArgumentException("Review Name is incorrect");
        }
    }

    public String bookmakerNameInHeader() {
        waitToElementIsVisible(bookmakerNameButtonElement);
        scrollIntoElement(bookmakerNameButtonElement);
        String elName = bookmakerNameButtonElement.getAttribute("outerText");
        return elName;
    }

    public String compareWithOthersInHeader() {
        waitToElementIsVisible(compareWithOthersButtonElement);
        scrollIntoElement(compareWithOthersButtonElement);
        String elName = compareWithOthersButtonElement.getAttribute("outerText");
        return elName;
    }

    public String websideInHeader() {
        waitToElementIsVisible(websiteButtonElement);
        scrollIntoElement(websiteButtonElement);
        String elName = websiteButtonElement.getAttribute("outerText");
        return elName;
    }
}
