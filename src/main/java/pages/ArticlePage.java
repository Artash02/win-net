package pages;

import api.ArticleApiCalls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlePage extends MainPage {

    @FindBy(className = "article-content")
    private WebElement articleContentElement;

    @FindBy(className = "article-share-buttons")
    private WebElement articleShareButtonsElement;

    @FindBy(className = "timeline-Header")
    private WebElement tweetElement;

    public ArticlePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnLink() {
        waitToElementIsVisible(articleContentElement);
        WebElement urlElement = articleContentElement.findElement(By.tagName("p")).findElement(By.tagName("a"));
        clickOnElement(urlElement);
    }

    public void clickOnShareButton(String socialPage) throws InterruptedException {
        Thread.sleep(5000);
        waitToElementIsVisible(articleShareButtonsElement);
        List<WebElement> socialElements = articleShareButtonsElement.findElements(By.tagName("button"));
        for (WebElement socialElement : socialElements) {
            if (socialElement.getAttribute("aria-label").equals(socialPage)) {
                clickOnElement(socialElement);
                break;
            }
        }
    }

    public HashMap<String, String> getHeading() {
        HashMap<String, String> headingValues = new HashMap<>();
        waitToElementIsVisible(articleContentElement);
        headingValues.put("h2", articleContentElement.findElement(By.tagName("h2")).getText());
        headingValues.put("h3", articleContentElement.findElement(By.tagName("h3")).getText());
        headingValues.put("h4", articleContentElement.findElement(By.tagName("h4")).getText());
        headingValues.put("h5", articleContentElement.findElement(By.tagName("h5")).getText());
        headingValues.put("h6", articleContentElement.findElement(By.tagName("h6")).getText());
        System.out.print(headingValues);
        return headingValues;
    }

    public float getImageSizeInArticlePage() throws InterruptedException {
        Thread.sleep(2000);
        String imageSrc = webDriver.findElement(By.xpath("//img[@alt=" + "'" + ArticleApiCalls.articleName + "'" + "]")).getAttribute("src");
        assert imageSrc.contains("progressive");
        String imageWidth = imageSrc.split("/h/")[0].split("w/")[1];
        String imageHeight = imageSrc.split("/h/")[1].split("/format")[0];
        int width = Integer.parseInt(imageWidth);
        int height = Integer.parseInt(imageHeight);
        return (float) width / (float) height;
    }


    public String getImageUrl() throws InterruptedException {
        Thread.sleep(2000);
        String imageSrc = webDriver.findElement(By.xpath("//img[@alt=" + "'" + ArticleApiCalls.articleName + "'" + "]")).getAttribute("src");
        assert imageSrc.contains("progressive");
        return imageSrc;
    }

    public float getImageSizeOnNewPage() {
        String imageSrc = webDriver.findElement(By.xpath("//img")).getAttribute("src");
        assert imageSrc.contains("progressive");
        String imageWidth = imageSrc.split("/h/")[0].split("w/")[1];
        String imageHeight = imageSrc.split("/h/")[1].split("/format/")[0];
        int width = Integer.parseInt(imageWidth);
        int height = Integer.parseInt(imageHeight);
        return (float) width / (float) height;
    }

    public ArrayList<String> getBulletOrNumberedList(String listType) {
        WebElement webElement;
        ArrayList<String> listItems = new ArrayList<>();
        webElement = switch (listType) {
            case "Bullet" -> webElement = articleContentElement.findElement(By.tagName("ul"));
            case "Numbered" -> webElement = articleContentElement.findElement(By.tagName("ol"));
            default -> throw new IllegalStateException("Unexpected value: " + listType);
        };
        List<WebElement> elements = webElement.findElements(By.tagName("li"));
        for (WebElement element : elements) {
            listItems.add(element.getText());
        }
        return listItems;
    }

    public String getTweetText() throws InterruptedException {
        waitToElementIsVisible(articleContentElement);
        Thread.sleep(4000);
        webDriver.switchTo().frame(webDriver.findElement(By.id("twitter-widget-0")));
        waitToElementIsVisible(tweetElement);
        scrollIntoElement(tweetElement);
        return tweetElement.getText();
    }

    public void clickOnYoutubePLayButton() {
        scrollIntoElement(webDriver.findElement(By.className("video-wrapper")));
        webDriver.switchTo().frame(0);
        clickOnElement(webDriver.findElement(By.className("ytp-large-play-button")));
        waitToElementIsVisible(webDriver.findElement(By.className("playing-mode")));
    }

    public void switchToIframe() {
        webDriver.switchTo().frame(0);
    }

}






