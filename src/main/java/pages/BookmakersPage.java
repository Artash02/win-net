package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookmakersPage extends MainPage{
    @FindBy(className = "overview-title")
    private WebElement overviewTitleElement;

    public BookmakersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getOverviewTitleText() {
        waitToElementIsVisible(overviewTitleElement);
        scrollIntoElement(overviewTitleElement);
        return overviewTitleElement.getText();
    }
}
