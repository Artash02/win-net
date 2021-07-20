package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RedditPage extends MainPage{

    @FindBy(id ="loginUsername")
    private WebElement loginElement;

    @FindBy(id ="loginPassword")
    private WebElement passwordElement;

    @FindBy(className ="AnimatedForm__submitButton")
    private WebElement loginButtonElement;

    @FindBy(className = "anPJr_ybRailY8NbAunl2")
    private WebElement chooseCommunityElement;

    @FindBy(tagName = "blockquote")
    private WebElement blockQuoteElement;


    public RedditPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillUsername() {
        fillText(loginElement, "Artashprio");
    }

    public void fillPassword() {
        fillText(passwordElement, "prio1234!");
    }

    public void clickOnLogin() {
        clickOnElement(loginButtonElement);
    }

    public void chooseCommunity() {
        waitToElementClickable(chooseCommunityElement);
        clickOnElement(chooseCommunityElement);
        chooseCommunityElement.sendKeys(Keys.ENTER);
    }

    public String getBlockQuoteText() {
        waitToProgressBarIsNotDisplayed();
        waitToElementIsVisible(blockQuoteElement);
        return blockQuoteElement.getText();
    }
}
