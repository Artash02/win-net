package helper;

import config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Log;

import java.net.URL;
import java.util.Map;

public class Factory {

    public static WebDriver createWebDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("sessionTimeout", "5m");
            final WebDriver driver;
            AppConfig appConfig = ConfigFactory.create(AppConfig.class);
            Log.warn("ENVIRONMENT_NAME " + appConfig.getEnvironment());
            Log.warn("BASE_URL " + appConfig.getBaseUrl());
            if (appConfig.getEnvironment().equals("local")) {
                Log.warn("news service " + appConfig.getApiNewsServiceUrl());
                Log.warn("Run on 'Local'");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(capabilities);
            } else {
                capabilities.setCapability("browserName", appConfig.getBrowserName());
                capabilities.setCapability("browserVersion", appConfig.getChromeBrowserVersion());
                capabilities.setCapability("selenoid:options", Map.of("enableVNC", true, "enableVideo", false));
                capabilities.setCapability("sessionTimeout", "5m");
                Log.warn(appConfig.getSelenoidHubUrl());
                driver = new RemoteWebDriver(new URL(appConfig.getSelenoidHubUrl()), capabilities);
            }
            return driver;
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}