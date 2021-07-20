package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BackOfficePage extends MainPage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailElement;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordElement;

    @FindBy(className = "LoginButton")
    private WebElement loginButtonElement;

    @FindBy(xpath = "//input[@aria-label='Description']")
    private WebElement searchFieldElement;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorPrimary']")
    private WebElement openArticleInNewTabElement;

    @FindBy(css = ".MuiTableRow-head")
    private WebElement tableHeadElement;

    @FindBy(className = "TableRow")
    private List<WebElement> tableRowElement;

    public BackOfficePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillEmail(String value) {
        fillText(emailElement, value);
    }

    public void fillPassword(String value) {
        fillText(passwordElement, value);
    }

    public void clickOnLoginButton() {
        clickOnElement(loginButtonElement);
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("ImgContainer")));
    }

    public void fillValueInSearchField(String value) {
        fillText(searchFieldElement, value);
    }

    public void clickOnArticle(String articleName) {
        String xpath = "//a[@class='MuiTypography-root MuiLink-root MuiLink-underlineHover TitleLink MuiTypography-colorPrimary'][text()='article_name']";
        clickOnElement(webDriver.findElement(By.xpath(xpath.replace("article_name", articleName))));
    }

    public void clickOnOpenArticleInNewTabButton() {
        waitToProgressBarIsNotDisplayed();
        waitToElementClickable(openArticleInNewTabElement);
        clickOnElement(openArticleInNewTabElement);
    }

    public void navigateSecondWindow() {
        Set<String> windows = webDriver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
//        String parentWindow = itr.next();
        String childWindow = itr.next();
        webDriver.switchTo().window(childWindow);
//        articleUrl = webDriver.getCurrentUrl();
    }

    public void selectColumnByName(String columnName) {
        String xpath = "//span[text()='column_name']";
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("MuiTable-root")));
        WebElement columnElement = webDriver.findElement(By.xpath(xpath.replace("column_name", columnName)));
        clickOnElement(columnElement);
    }

    public ArrayList getBookmakerItemsByName(String columnName) {
        ArrayList<String> bookmakerNames = new ArrayList<>();
        int columnIndex = 0;
        List<WebElement> columNameElements = tableHeadElement.findElements(By.tagName("th"));
        for (WebElement columNameElement : columNameElements) {
            if (columNameElement.getText().equals(columnName)) {
                columnIndex = columNameElements.indexOf(columNameElement);
                break;
            }
        }
        for (WebElement rowElement : tableRowElement) {
            bookmakerNames.add(rowElement.findElements(By.tagName("td")).get(columnIndex).getText());
        }
        return bookmakerNames;
    }

    public boolean checkIfDescendingSortedBookmakerNames(ArrayList<String> arraylist) {
        boolean isSorted = true;
        for (int i = 1; i < arraylist.size(); i++) {
            if (arraylist.get(i - 1).compareTo(arraylist.get(i)) < 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public boolean checkIfAscendingSortedBookmakerNames(ArrayList<String> arraylist) {
        boolean isSorted = true;
        for (int i = 1; i < arraylist.size(); i++) {
            if (arraylist.get(i - 1).compareTo(arraylist.get(i)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public boolean checkIfRatingSorted(ArrayList<String> arrayList) {
        ArrayList<Float> ratingsList = new ArrayList<Float>();
        for (String ratingText : arrayList) {
            float rating = Float.parseFloat(ratingText.split("/")[0].trim());
            ratingsList.add(rating);
        }
        boolean isSorted = true;
        for (int i = 1; i < arrayList.size(); i++) {
            if (ratingsList.get(i) - (ratingsList.get(i - 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public boolean checkIfDatesDescendingSorted(ArrayList<String> arrayList) throws ParseException {
        ArrayList<Date> datesList = new ArrayList<>();
        for (String dateText : arrayList) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");
            ;
            Date date1 = format.parse(dateText);
            datesList.add(date1);
        }
        boolean isSorted = true;
        for (int i = 1; i < arrayList.size(); i++) {
            if (datesList.get(i).compareTo(datesList.get(i - 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }


}
