package pages;

import api.BookmakerApiCalls;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BookmakersTablePage extends MainPage {

    final BookmakerApiCalls bookmakerApiCalls = new BookmakerApiCalls();

    @FindBy(className = "bookmaker-list-items")
    public WebElement bookmakersListElement;

    @FindBy(className = "tt-bookmaker-toolbar-sort")
    private WebElement sortByDropdownElement;

    @FindBy(className = "bookmaker-list-empty")
    private WebElement bookmakerListEmptyElement;

    @FindBy(className = "bookmaker-item-score")
    private List<WebElement> bookmakerItemScoreElements;

    @FindBy(className = "search-items")
    private WebElement searchItemsElement;

    @FindBy(xpath = "//a[@class='flex-all-center']")
    private List<WebElement> bookmakerNameElements;

    @FindBy(className = "BookmakerItem")
    private List<WebElement> bookmakerItemElements;

    @FindBy(className = "bookmaker-show-all-btn")
    private WebElement showButtonElement;

    @FindBy(className = "ChartLegend")
    public WebElement chartLegendElement;

    public BookmakersTablePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnBookmakerReview(String value) {
        waitToElementIsVisible(bookmakersListElement);
        List<WebElement> bookmakerListItems = bookmakersListElement.findElements(By.className("flex-all-center"));
        for (WebElement bookmakerListItem : bookmakerListItems) {
            if (bookmakerListItem.getAttribute("aria-label").equals(value)) {
                clickOnElement(bookmakerListItem);
                break;
            }
        }
    }

    public String valueInSortByDropDown() throws InterruptedException {
        Thread.sleep(5000);
        clickOnElement(webDriver.findElement(By.className("RankingSelector")));
        waitToElementIsVisible(searchItemsElement);
        WebElement itemElement = searchItemsElement.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[3]/div"));
        return itemElement.getText();
    }

    public List<String> getListOfCheckedBookmakers() throws InterruptedException {
        List<String> bookmakersTmpList = new ArrayList<>();
        int cnt = 0;
        Thread.sleep(1000);
        for (int i = 1; i < getBookmakersCount() + 1; ++i) {
            Thread.sleep(300);

            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]"));
            if (el.getAttribute("className").contains("selected")) {
                ++cnt;
                WebElement elBookmakerName = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
                bookmakersTmpList.add((String) elBookmakerName.getAttribute("title"));
            }
        }
        System.out.println("\n" + cnt + " Bookmakers were checked out of " + getBookmakersCount());
        System.out.println(bookmakersTmpList);
        return (bookmakersTmpList);
    }

    public int numberOfCheckedBookmakers(List args) {
        return args.size();
    }

    public Integer getNumberOfDisabledBookmakers() throws InterruptedException {

        int cnt = 0;
        Thread.sleep(1000);
        for (int i = 1; i < getBookmakersCount() + 1; ++i) {
            Thread.sleep(300);
            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]"));
            if (el.getAttribute("className").contains("disabled")) {
                ++cnt;
            }
        }
        System.out.println("\n" + cnt + " Bookmakers were disabled out of " + getBookmakersCount());
        return (getBookmakersCount() - cnt);
    }

    public boolean bookmakerNameInBookmakerTable(String args) {
        boolean a = false;
        for (WebElement bookmakerItemElement : bookmakerItemElements) {
            if (args.equals(bookmakerItemElement)) {
                a = true;
                break;
            } else {
                a = false;
            }
        }
        return a;
    }

    public boolean isElementPresent(By locatorKey) {
        try {
            webDriver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void waitToElementIsVisibleNew() throws TimeoutException {
        this.webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bookmaker-list-items")));
    }

    public List<Object> getBookmakersName()  {
        ArrayList bookmakersList = new ArrayList<>();
        waitToProgressBarIsNotDisplayed();
        //waitToElementIsVisibleNew();
        if (isElementPresent(By.className("bookmaker-list-items"))) {
            List<WebElement> bookmakerItems = bookmakersListElement.findElements(By.className("BookmakerItem"));
            for (WebElement bookmakerItem : bookmakerItems) {
                WebElement bookmakerItemBookmakerElement = bookmakerItem.findElement(By.className("bookmaker-item-bookmaker")).findElement(By.tagName("a"));
                if (bookmakerItemBookmakerElement.getAttribute("next-link").equals("true")) {
                    bookmakersList.add(bookmakerItemBookmakerElement.getAttribute("aria-label"));
                }
            }
            Log.warn(String.valueOf(bookmakersList));
        } else {
            return Collections.emptyList();
        }
        return bookmakersList;
    }

    public boolean checkEmptyBookmakersTable() {
        waitToElementIsVisible(bookmakerListEmptyElement);
        return true;
    }

    public void selectValueInSortByDropDown(String value) throws InterruptedException {
        Thread.sleep(5000);
        clickOnElement(webDriver.findElement(By.className("RankingSelector")));
        waitToElementIsVisible(searchItemsElement);
        List<WebElement> searchItemElements = searchItemsElement.findElements(By.className("search-item"));
        clickOnElement(findElementByText(searchItemElements, value));
    }

    public Integer returnServicePositionFromRatingsList(String args) {

        Integer ratingPosition = null;
        switch (args) {
            case ("Rankings") -> ratingPosition = 0;
            case ("Bonus Amount") -> ratingPosition = 1;
            case ("Sport / Game Coverage") -> ratingPosition = 2;
            case ("Responsible Gaming") -> ratingPosition = 3;
            case ("Deposit & Withdrawal") -> ratingPosition = 4;
            case ("Onboarding Process") -> ratingPosition = 5;
            case ("Customer Service") -> ratingPosition = 6;
            case ("Utility Features") -> ratingPosition = 7;
        }
        return ratingPosition;
    }

    public void selectBookmakerFromBookmakersTable(String bookmakerName) throws InterruptedException {
        waitToProgressBarIsNotDisplayed();
        clickOnShowAllButtonIfDisplayed();
        for (WebElement bookmakerNameElement : bookmakerNameElements) {
            if (bookmakerNameElement.getAttribute("aria-label").equals(bookmakerName)) {
                clickOnElement(bookmakerNameElement);
                waitToProgressBarIsNotDisplayed();
                break;
            }
        }
    }

    public ArrayList getSortByDropDownOptions() {
        ArrayList dropDownOptionsList = new ArrayList<>();
        List<WebElement> dropDownOptionElements = searchItemsElement.findElements(By.className("search-item"));
        for (WebElement dropDownOptionElement : dropDownOptionElements) {
            dropDownOptionsList.add(dropDownOptionElement.getText());
        }
        return dropDownOptionsList;
    }

    public void clickOnButtonsByBookmakerName(String bookmakerName, String buttonName) throws InterruptedException {
        Thread.sleep(7000);
        clickOnShowAllButtonIfDisplayed();
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem selectable']"));
        WebElement buttonElement = switch (buttonName) {
            case "Website" -> bookmakerItemElement.findElement(By.className("bookmaker-item-website"));
            case "Full Review" -> bookmakerItemElement.findElement(By.className("bookmaker-item-review"));
            case "Plus" -> bookmakerItemElement.findElement(By.className("bookmaker-item-select"));
            case "Expand" -> bookmakerItemElement.findElement(By.className("bookmaker-item-toggle"));
            case "Logo" -> bookmakerItemElement.findElement(By.className("bookmaker-logo"));
            default -> throw new IllegalArgumentException("Content not found");
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public void clickOnButtonsByBookmakerNameOnSportsBettingSitesPage(String bookmakerName, String buttonName) throws InterruptedException {
        Thread.sleep(5000);
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label='" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem']"));
        WebElement buttonElement = switch (buttonName) {
            case "Website" -> bookmakerItemElement.findElement(By.className("bookmaker-item-website"));
            case "Full Review" -> bookmakerItemElement.findElement(By.className("bookmaker-item-review"));
            case "Plus" -> bookmakerItemElement.findElement(By.className("bookmaker-item-select"));
            case "Expand" -> bookmakerItemElement.findElement(By.className("bookmaker-item-toggle"));
            case "Logo" -> bookmakerItemElement.findElement(By.className("bookmaker-logo"));
            default -> throw new IllegalArgumentException("Content not found");
        };
        scrollIntoElement(buttonElement);
        clickOnElement(buttonElement);
    }

    public String getScoreColorByBookmakerName(String bookmakerName) throws InterruptedException, IllegalArgumentException {
        Thread.sleep(6000);
        clickOnShowAllButtonIfDisplayed();
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem selectable']"));
        scrollIntoElement(bookmakerItemElement);
        WebElement bookmakerItemScoreElement = bookmakerItemElement.findElement(By.className("bookmaker-item-score"));
        String scoreText = bookmakerItemScoreElement.getText().split("/")[0].trim();
        float score = Float.parseFloat(scoreText);
        String color = null;
        if (score <= 10 && score >= 8) {
            color = "Strong green";
        }
        if (score <= 8 && score >= 6) {
            color = "Citrus";
        }
        if (score <= 6 && score >= 3) {
            color = "Sunglow";
        }
        if (score <= 3 && score >= 0) {
            color = "Radical Red";
        }
        Log.warn("color is " + color);
        return color;
    }

    public String getSelectedItemInSortByDropDown() {
        String selectedValue = null;
        List<WebElement> searchItem = searchItemsElement.findElements(By.className("search-item"));
        for (WebElement element : searchItem)
            if (element.getAttribute("class").contains("selected")) {
                selectedValue = element.getText();
            }
        return selectedValue;
    }

    public boolean checkIfBookmakerScoresAreDescendingSorted() {
        ArrayList<Float> bookmakersScore = new ArrayList<Float>();
        for (WebElement bookmakerScoreElement : bookmakerItemScoreElements) {
            float score = Float.parseFloat(bookmakerScoreElement.getText().split("/")[0].trim());
            bookmakersScore.add(score);
        }
        boolean sorted = true;
        for (int i = 1; i < bookmakersScore.size(); i++) {
            if (bookmakersScore.get(i) - (bookmakersScore.get(i - 1)) > 0) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public int getBookmakersCount() {
        clickOnShowAllButtonIfDisplayed();
        waitToProgressBarIsNotDisplayed();
        int bookmakersCount = 0;
        if (bookmakerItemElements.size() > 0) {
            bookmakersCount = bookmakerItemElements.size();
        }
        return bookmakersCount;
    }

    public boolean isBookmakerExpanded(String bookmakerName) {
        try {
            webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label=" + "'" + bookmakerName + "']" + "/ancestor::div[@class='BookmakerItem ShowContent']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public List<String> getFirstNBookmakersInTable(Integer args) throws InterruptedException {
        Thread.sleep(3000);
        ArrayList firstNBookmakers = new ArrayList<>();

        for (int i = 1; i < args + 1; ++i) {
            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
            String bookmakerNameFromTable = el.getAttribute("title");
            firstNBookmakers.add(bookmakerNameFromTable);
        }
        return firstNBookmakers;
    }

    public boolean isAllBookmakersCollapsed() {
        boolean visible = true;
        for (WebElement bookmakerItemElement : bookmakerItemElements) {
            if (bookmakerItemElement.getAttribute("class").contains("ShowContent")) {
                visible = false;
            }
        }
        return visible;
    }

    public boolean isShowAllBookmakersButtonVisible() {
        waitToProgressBarIsNotDisplayed();
        return isElementPresent(By.className("bookmaker-show-all-btn"));
    }

    public boolean isBookmakerContentVisibleForFirstBookmaker() {
        boolean visible = false;
        if (bookmakerItemElements.get(0).findElement(By.className("bookmaker-item-content")).isDisplayed()) {
            visible = true;
        }
        return visible;
    }

    public String getButtonName() {
        waitToElementIsVisible(showButtonElement);
        return showButtonElement.getText();
    }

    public void clickOnShowAllButtonIfDisplayed() {
        try {
            showButtonElement.isDisplayed();
            scrollIntoElement(showButtonElement);
            if (showButtonElement.getAttribute("outerText").equals("SHOW MORE BOOKMAKERS")) {
                clickOnElement(showButtonElement);
                scrollIntoElement(chartLegendElement);
            }
            assert (urlContains("showAllBookmaker=true"));
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log.warn("SHOW FEWER BOOKMAKERS BUTTON IS VISIBLE");
        }
     }

    public String getBookmakerInfoByColumnName(String bookmakerName, String columnName) throws InterruptedException {
        Thread.sleep(5000);
        clickOnShowAllButtonIfDisplayed();
        WebElement bookmakerItemElement = webDriver.findElement(By.xpath("//a[@class='flex-all-center'][@aria-label='" + bookmakerName + "'" +
                "]/ancestor::div[@class='bookmaker-item-bookmaker']/ancestor::div[@class='BookmakerItem']"));
        WebElement infoElement = switch (columnName) {
            case "Bonus" -> bookmakerItemElement.findElement(By.className("bookmaker-item-bonus"));
            default -> throw new IllegalArgumentException(columnName + " Content not found");
        };
        waitToElementIsVisible(infoElement);
        scrollIntoElement(infoElement);
        return infoElement.getText();
    }

    public boolean checkBookmakerSortingByCriteriaName(String criteriaName) {
        boolean bool = true;

        Response responseVariable = bookmakerApiCalls.getBookmakersDataFromApi();
        List bookmakersListVariable = getBookmakersName();

        ArrayList ratingsListExtractedFromApiForTheCurrentBookmaker = bookmakerApiCalls.getRatingsFromApiByBookmakerName
                (String.valueOf(bookmakersListVariable.get(0)), responseVariable);
        for (int i = 0; i < bookmakersListVariable.size() - 1; ++i) {
            ArrayList ratingsListExtractedFromApiForTheNextBookmaker = bookmakerApiCalls.getRatingsFromApiByBookmakerName
                    (String.valueOf(bookmakersListVariable.get(i + 1)), responseVariable);//gets the rating list only for next bookmaker
            if ((Float) Float.parseFloat(String.valueOf(ratingsListExtractedFromApiForTheCurrentBookmaker
                    .get(returnServicePositionFromRatingsList(criteriaName))))
                    < (Float) Float.parseFloat(String.valueOf(ratingsListExtractedFromApiForTheNextBookmaker
                    .get(returnServicePositionFromRatingsList(criteriaName))))) {
                Log.warn("'" + criteriaName + "' filter: wrong sorting between bookmakers '" +
                        bookmakersListVariable.get(i) + "' & '" + bookmakersListVariable.get(i + 1)
                        + "'; listed in the positions: " + (i + 1) + " & " + (i + 2) + ".");
                bool = false;
            }
            ratingsListExtractedFromApiForTheCurrentBookmaker = ratingsListExtractedFromApiForTheNextBookmaker;
        }
        return bool;
    }
    public void scrollToBottomOfBookmakerTable() {
        List<WebElement> el = webDriver.findElements(By.className("BookmakerItem"));
        System.out.println(el);
        for (WebElement i : el) {
            scrollIntoElement(i);
        }
    }

    public int getBookmakersCurrentCount() {
        int bookmakersCount = 0;
        if (bookmakerItemElements.size() > 0) {
            bookmakersCount = bookmakerItemElements.size();
        }
        return bookmakersCount;
    }

    public String getShowAllBookmakersButtonCurrentText() {
        return showButtonElement.getAttribute("outerText");
    }
}












