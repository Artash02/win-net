package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class AnalysisPage extends MainPage {

    @FindBy(className = "article-title")
    private List<WebElement> articleTitleElements;

    @FindBy(className = "NewsSubheader")
    private WebElement analysisPageSubheaderElement;

    @FindBy(className = "article-text")
    private List<WebElement> articleTextElements;

    public AnalysisPage(WebDriver webDriver) {
        super(webDriver);
    }



    public String getAnalysisSubheaderText() {
        return analysisPageSubheaderElement.getText();
    }

    public void selectArticle(String articleName) {
        clickOnElement(findElementByText(articleTitleElements,articleName));
    }

    public void selectArticleByNumber(int num) {
        waitToElementIsVisible(articleTitleElements.get(num));
        scrollIntoElement(articleTitleElements.get(num));
        articleTitleElements.get(num).click();
    }

    public String getSelectedTagForArticle(String articleName) {
        String selectedTag = null;
        for (WebElement articleTextElement : articleTextElements) {
            waitToElementIsVisible(articleTextElement);
            if (articleTextElement.getText().contains(articleName)) {
                selectedTag = articleTextElement.findElement(By.className("article-tag")).getText();
            }
        }
        return selectedTag;
    }

    public String getFirstArticleName() {
        return articleTitleElements.get(0).getText();
    }

    public String getSelectedInfoByArticleNameAndInfoName(String articleName, String infoName) {
        WebElement webElement = null;
        for (WebElement articleTextElement : articleTextElements) {
            waitToElementIsVisible(articleTextElement);
            if (articleTextElement.getText().contains(articleName)) {
                webElement = switch (infoName) {
                    case "article subtext" -> articleTextElement.findElement(By.className("article-subtext"));
                    case "article info" -> articleTextElement.findElement(By.className("article-info"));
                    case "article tag" -> articleTextElement.findElement(By.className("article-info")).findElement(By.className("article-tag"));
                    default -> throw new IllegalArgumentException();
                };
            }
        }
        assert webElement != null;
        return webElement.getText().replace("\n", " ");
    }

    public float getImageSize() throws InterruptedException {
        Thread.sleep(2000);
        String imageSrc = webDriver.findElements(By.xpath("//img")).get(1).getAttribute("src");
        assert imageSrc.contains("progressive");
        String imageWidth = imageSrc.split("/h/")[0].split("w/")[1];
        String imageHeight = imageSrc.split("/h/")[1].split("/format")[0];
        int width = Integer.parseInt(imageWidth);
        int height = Integer.parseInt(imageHeight);
        return (float) width / (float) height;
    }

}
