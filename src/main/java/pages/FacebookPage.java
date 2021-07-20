package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookPage extends BasePage {

    @FindBy(id = "email")
    private WebElement idElement;

    @FindBy(id = "pass")
    private WebElement passwordElement;

    @FindBy(id = "loginbutton")
    private WebElement loginElement;

    @FindBy(xpath = "//button[@name='__CONFIRM__']")
    private WebElement publishArticleElement;

    @FindBy(className = "oajrlxb2")
    private WebElement profileElement;

    @FindBy(className = "ow4ym5g4")
    private WebElement viewProfileElement;

    public FacebookPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillEmail() throws InterruptedException {
        Thread.sleep(4000);
        fillText(idElement, "artashes@priotix.com");
    }

    public void fillPassword() {
        fillText(passwordElement, "prio1234!");
    }

    public void clickOnLogin() {
        clickOnElement(loginElement);
    }

    public void clickOnPublishArticleButton() {
        clickOnElement(publishArticleElement);
    }

    public void clickOnProfile() {
        clickOnElement(profileElement);
    }

    public void clickOnViewProfile() {
        clickOnElement(viewProfileElement);
    }
}