package tests.base;

import api.*;
import config.AppConfig;
import helper.Excel;
import helper.Factory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import rest.RequestHelpers;
import rest.RequestMethods;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.Log;
import java.lang.reflect.Method;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class BaseTest {
    public String baseUrl;
    public AppConfig appConfig;
    public Excel excel;
    public Authentication authentication;
    public ArticleApiCalls articleApiCalls;
    public BookmakerApiCalls bookmakerApiCalls;
    public QuickCardApiCalls quickCardApiCalls;
    public BackOfficeApiCalls backOfficeApiCalls;
    public ComparisonApiCalls comparisonApiCalls;
    public ThreadLocal<WebDriver> driver;
    public MainPage mainPage;
    public FilterPage filterPage;
    public FooterPage footerPage;
    public ArticlePage articlePage;
    public ReviewPage reviewPage;
    public QuickCardPage quickCardPage;
    public RecommenderPage recommenderPage;
    public RequestMethods requestMethods;
    public RequestHelpers requestHelpers;
    public AnalysisPage analysisPage;
    public FacebookPage facebookPage;
    public TwitterPage twitterPage;
    public RedditPage redditPage;
    public BookmakersTablePage bookmakersTablePage;
    public ComparisonPage comparisonPage;
    public BookmakersPage bookmakersPage;
    public BackOfficePage backOfficePage;
    public final SoftAssert softAssert = new SoftAssert();

    protected WebDriver webDriver() {
        return driver.get();
    }

//    @BeforeSuite
//    public void beforeSuite() throws ConfigurationException {
//        PropertiesConfiguration configuration = new PropertiesConfiguration("src/main/resources/reportportal.properties");
//        configuration.setProperty("rp.project", "winners_net_" + System.getenv().get("HOST_ENV"));
//        configuration.save();
//    }

    @BeforeClass(alwaysRun = true, description = "Before class")
    public void setup() throws Exception {
        appConfig = ConfigFactory.create(AppConfig.class);
        baseUrl = appConfig.getBaseUrl();
        driver = new ThreadLocal<>();
        driver.set(Factory.createWebDriver());
        driver.get().manage().window().maximize();
        mainPage = PageFactory.initElements(webDriver(), MainPage.class);
        filterPage = PageFactory.initElements(webDriver(), FilterPage.class);
        footerPage = PageFactory.initElements(webDriver(), FooterPage.class);
        bookmakersTablePage = PageFactory.initElements(webDriver(), BookmakersTablePage.class);
        comparisonPage = PageFactory.initElements(webDriver(), ComparisonPage.class);
        articlePage = PageFactory.initElements(webDriver(), ArticlePage.class);
        reviewPage = PageFactory.initElements(webDriver(), ReviewPage.class);
        analysisPage = PageFactory.initElements(webDriver(), AnalysisPage.class);
        facebookPage = PageFactory.initElements(webDriver(), FacebookPage.class);
        twitterPage = PageFactory.initElements(webDriver(), TwitterPage.class);
        redditPage = PageFactory.initElements(webDriver(), RedditPage.class);
        bookmakersPage = PageFactory.initElements(webDriver(), BookmakersPage.class);
        recommenderPage = PageFactory.initElements(webDriver(), RecommenderPage.class);
        quickCardPage = PageFactory.initElements(webDriver(), QuickCardPage.class);
        backOfficePage = PageFactory.initElements(webDriver(), BackOfficePage.class);
        excel = new Excel();
        authentication = new Authentication();
        articleApiCalls = new ArticleApiCalls();
        bookmakerApiCalls = new BookmakerApiCalls();
        quickCardApiCalls = new QuickCardApiCalls();
        backOfficeApiCalls = new BackOfficeApiCalls();
        comparisonApiCalls = new ComparisonApiCalls();
        requestMethods = new RequestMethods();
        requestHelpers = new RequestHelpers();
        webDriver().navigate().to(baseUrl);
    }

        @AfterMethod
        public void reportPortalscreenShot(ITestResult result){
        Log.warn("After Method");
        if(ITestResult.FAILURE==result.getStatus()){
            File file = new File("src/test/resources/" + "failed.png");
            BufferedImage screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(webDriver()).getImage();
            try {
                ImageIO.write(screenshot, "PNG",
                        file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ReportPortal.emitLog("screenshot", "FAIL", Calendar.getInstance().getTime(), file);
        }
    }

    @AfterMethod
    public void screenShot(ITestResult result,Method method) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            Screenshot myScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(webDriver());
            ImageIO.write(myScreenshot.getImage(), "PNG", new File("target/" + method.getName()+"screenshot.png"));
        }
    }

    @AfterClass(alwaysRun = true, description = "After method")
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}