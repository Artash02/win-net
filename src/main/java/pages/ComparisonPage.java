package pages;

import org.apache.commons.math3.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Log;

import java.io.IOException;
import java.util.*;

public class ComparisonPage extends MainPage {

    @FindBy(className = "ChartLegend")
    public WebElement chartLegendElement;

    @FindBy(css = ".left .btn")
    private List<WebElement> subTabChooseElements;

    public static String currentUrl;

    public ComparisonPage(WebDriver webDriver) {
        super(webDriver);
    }

    final Double dFrmt = 100000000000.0;

    public List<Pair<Double, Double>> getComparisonChartCoordinates() {
        List<WebElement> el = webDriver.findElements(By.xpath("//*[local-name()='svg' and @xmlns='http://www.w3.org/2000/svg']/*[local-name()='g'] /*[local-name()='g']/*[local-name()='g']"));
        List<Pair<Double, Double>> lstCoorDom = new ArrayList<>();
        for (WebElement z : el) {
            String j = z.getAttribute("innerHTML");
            if (j.contains("<path d=\"M0") && j.contains("Z\"")) {
                String[] pairs = j.substring(j.indexOf("<path d=\"M0"), j.indexOf("Z\""))
                        .replace("<path d=\"M", "").replace("z\"", "")
                        .replace(",", " ").split("L", 0);
                for (String pairStr : pairs) {
                    String[] pair = pairStr.split(" ");
                    lstCoorDom.add(Pair.create(Math.round(Double.parseDouble(pair[0]) * dFrmt) / dFrmt,
                            Math.round(Double.parseDouble(pair[1]) * dFrmt) / dFrmt));
                }
            }
        }
        return lstCoorDom;
    }

    public Number getOverallRatingFromApi(List<Number> args) {

        Number apiOverall = args.get(0);
        args.remove(0);
        System.out.println("\nAPI Overall Rating: " + apiOverall + "; Ratings by Services: " + args + "\n");
        return apiOverall;
    }

    public Double calculateOverallRatingOnDomCoordinates(List<Number> args) {

        args.remove(0);
        double overallRatingDom;
        double sumOfHypotenuses = 0.0;

        for (int i = 0; i < getComparisonChartCoordinates().size(); ++i) {
            sumOfHypotenuses = sumOfHypotenuses + Math.sqrt(Math.pow(getComparisonChartCoordinates().get(i).getFirst(), 2)
                    + Math.pow(getComparisonChartCoordinates().get(i).getSecond(), 2));
        }
        overallRatingDom = Math.round(10 * sumOfHypotenuses / (190 * getComparisonChartCoordinates().size()) * 10.0) / 10.0;
        //*10 for adjusting with 'a/10' format of overall rating on the site

        return overallRatingDom;
    }

    public List<Pair<Double, Double>> createCoordinatesOnApiRatings(List<Number> args) {
        List<Pair<Double, Double>> lstCoorApi = new ArrayList<>();

        args.remove(0);
        for (int i = 0; i < args.size(); i++) {
            lstCoorApi.add
                    (Pair.create(
                            Math.round(args.get(i).doubleValue() * 190 / 10 * Math.sin(360 * ((Math.PI / 180)
                                    / args.size()) * i) * dFrmt) / dFrmt,
                            Math.round(args.get(i).doubleValue() * 190 / 10 * Math.cos(360 * ((Math.PI / 180)
                                    / args.size()) * i) * -1 * dFrmt) / dFrmt));
            //*(-1) at the end is for rotating the ordinate (y) ax for 180 degrees as in chart; 190/10 pixels to rating units ratio
        }
        System.out.println("\nDOM: " + getComparisonChartCoordinates());
        System.out.println("API: " + lstCoorApi);
        return lstCoorApi;
    }

    public List<Number> getRatingsFromBookmakerPage() {
        List<Number> lstRat = new ArrayList<>();
        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div/aside/div/div/div/div/div[2]/div[1]"));
        lstRat.add(Float.parseFloat(el.getAttribute("innerText").replace(" / 10", "")));

        el = webDriver.findElement(By.xpath("//*[@id=\"bonus\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"responsibleGaming\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"depositAndWithdrawal\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"onboarding\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"customerServices\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        el = webDriver.findElement(By.xpath("//*[@id=\"utility\"]/div[1]/div[3]/span"));
        lstRat.add(Integer.parseInt(el.getAttribute("innerText").replace(" / 10", "")));
        System.out.println("Overall Rating: " + lstRat.get(0));
        return lstRat;
    }

    public ArrayList getNameFromLegendElement(int args) {

        ArrayList legendNames = new ArrayList();

        scrollIntoElement(chartLegendElement);

        if (args > 5) {args = 5;}
        for (int i = 1; i < args + 1; ++i) {
            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[1]/div[1]/div[2]/div[" + i + "]"));
            legendNames.add(el.getAttribute("textContent"));
        }
        Log.warn(String.valueOf(legendNames));
        return legendNames;
    }

    public ArrayList getNamesAndColorsFromLegend(Integer args) throws InterruptedException {

        scrollIntoElement(chartLegendElement);
        waitToProgressBarIsNotDisplayed();

        ArrayList allLegendElements = new ArrayList();

        if (args > 5) {args = 5;}
        for (int i = 1; i < args + 1; ++i) {

            WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[1]/div[1]/div[2]/div[" + i + "]"));
            String elString = el.getAttribute("innerHTML");
            if (elString.contains("background-color: ") && elString.contains("<div class=\"close-icon\"")) {
                elString = elString.substring(elString.indexOf("background-color: "), elString.indexOf("<div class=\"close-icon\""))
                        .replace("background-color: ", "")
                        .replace("<div class=\"close-icon\"", "")
                        .replace(";\"></div>", "")
                        .replace("rgb(9, 105, 250)", "blue ")
                        .replace("rgb(46, 194, 87)", "green ")
                        .replace("rgb(255, 33, 74)", "red ")
                        .replace("rgb(166, 63, 213)", "purple ")
                        .replace("rgb(255, 139, 30)", "orange ");
                allLegendElements.add(elString);
            }
        }
        System.out.println(allLegendElements);
        return allLegendElements;
    }

    public List<String> addNBookmakersFromNbr(Integer arg1, Integer arg2, String arg3) throws InterruptedException {

        waitToProgressBarIsNotDisplayed();
        List<String> bookmakersTmpList = new ArrayList<>();

        scrollIntoElement(chartLegendElement);

        if (arg1 > 5) {arg1 = 5;}
        if (arg3.equals("descending")) {
            for (int i = arg2; i > arg2 - arg1; --i) {
                WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
                bookmakersTmpList.add(el.getAttribute("title"));
                el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]"));
                waitToElementClickable(el);
                el.click();
            }
        } else {
            for (int i = arg2; i < arg2 + arg1; ++i) {
                WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[1]/a/img"));
                bookmakersTmpList.add(el.getAttribute("title"));
                Thread.sleep(1000);
                el=webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[2]/div[2]/div/div[" + i + "]/div[4]"));
                waitToElementClickable(el);
                el.click();
            }
        }
        System.out.println(bookmakersTmpList);
        return bookmakersTmpList;
    }

    public int numberOfChartsInLegend(List args) {
        return args.size();
    }

    public boolean checkIfButtonIsActive(String args) {
        waitToProgressBarIsNotDisplayed();
        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"MainContent\"]/div[1]/div[1]/button[2]"));
        waitToElementIsVisible(el);
        if (el.getAttribute("outerText").contains(args) && el.getAttribute("className").contains("case active")) {
            return true;
        } else {
            return false;
        }
    }

    public void switchToNextTab(){
        waitToProgressBarIsNotDisplayed();
        ArrayList<String> tabs2 = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));
    }

    public void saveCurrentUrl() {
        currentUrl = getCurrentUrl();
    }

    public void chooseComparisonSubTabByName(String tabName) {
        waitToProgressBarIsNotDisplayed();
        clickOnElement(findElementByText(subTabChooseElements, tabName));
    }
}