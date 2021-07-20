package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickCardPage extends MainPage {

    @FindBy(className = "QuickCardContainer")
    public List<WebElement> quickCardContainerElements;

    @FindBy(className = "QuickCardContainer")
    public WebElement quickCardContainerElement;


    public QuickCardPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getQuickCardInfo(Integer place) throws InterruptedException {
        scrollIntoElement(quickCardContainerElement);
        String contentItems=null;Integer cnt=0;
        for (WebElement quickCardContainerElement : quickCardContainerElements) {
            ++cnt;
            if(cnt==place){
                contentItems=quickCardContainerElement.getAttribute("innerHTML").substring(quickCardContainerElement.getAttribute("innerHTML").indexOf("title=") + 7,
                        quickCardContainerElement.getAttribute("innerHTML").indexOf("alt") - 2) + ", " + quickCardContainerElement.getAttribute("innerText").replace("\n", ", ");
            }
        }
        System.out.println(String.valueOf(place)+", "+contentItems);
        return contentItems;
    }

    public void clickOnQuickCardButton(String bookmakerName, String buttonName) {
        WebElement webElement;
        String xpath = null;
        for (WebElement quickCardContainer : quickCardContainerElements) {
            webElement = quickCardContainer.findElement(By.tagName("img"));
            if (webElement.getAttribute("title").equals(bookmakerName)) {
                xpath = "//img[@title='" + bookmakerName + "']/ancestor::div[@class='QuickCardContainer']";
                break;
            }
        }
        assert xpath != null;
        WebElement quickCardElement = webDriver.findElement(By.xpath(xpath));
        WebElement buttonElement;
        scrollIntoElement(quickCardElement);
        buttonElement = switch (buttonName) {
            case "website" -> quickCardElement.findElement(By.cssSelector(".buttons .website-btn"));
            case "review" -> quickCardElement.findElement(By.cssSelector(".buttons .review-btn"));
            default -> throw new IllegalStateException("Unexpected value: " + buttonName);
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public ArrayList getQuickCardTags(String bookmakerName) {
        ArrayList tagNames = new ArrayList();
        WebElement webElement;
        String xpath = null;
        for (WebElement quickCardContainer : quickCardContainerElements) {
            webElement = quickCardContainer.findElement(By.tagName("img"));
            if (webElement.getAttribute("title").equals(bookmakerName)) {
                xpath = "//img[@title='" + bookmakerName + "']/ancestor::div[@class='QuickCardContainer']";
                break;
            }
        }
        assert xpath != null;
        WebElement quickCardElement = webDriver.findElement(By.xpath(xpath));
        scrollIntoElement(quickCardElement);
        List<WebElement> tagElements = quickCardElement.findElement(By.className("tags")).findElements(By.tagName("img"));
        for(WebElement tagElement: tagElements) {
            tagNames.add(tagElement.getAttribute("title"));
        }
        Log.warn(String.valueOf(tagNames));
        return tagNames;
    }

    public boolean isElementPresent() {
        try {
            webDriver.findElement(By.className("QuickCard"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public int quickCardCount() {
        return quickCardContainerElements.size();
    }

}
