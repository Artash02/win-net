package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TwitterPage extends BasePage{


    @FindBy(xpath ="//input[@name='session[username_or_email]']")
    private WebElement loginElement;

    @FindBy(xpath ="//input[@name='session[password]']")
    private WebElement passwordElement;

    @FindBy(xpath ="//span[text()='Log in']")
    private WebElement loginButtonElement;


    public TwitterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillEmail() {
        fillText(loginElement, "artashes@priotix.com");
    }

    public void fillPassword() {
        fillText(passwordElement, "prio1234!");
    }

    public void clickOnLogin() {
       clickOnElement(loginButtonElement);
    }

}
