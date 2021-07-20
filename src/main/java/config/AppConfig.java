package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/java/config/${JAVA_ENV}.properties",
        "file:src/main/java/config/default.properties",
        "system:properties",
        "system:env"
})

public interface AppConfig extends Config {

    @Key("JAVA_ENV")
    String getEnvironment();

    @Key("BASE_URL")
    String getBaseUrl();

    @Key("BACKOFFICE_HOST")
    String getBackOfficeUrl();

    @Key("BACKOFFICE_USERNAME")
    String getBackofficeUsername();

    @Key("BACKOFFICE_PASSWORD")
    String getBackOfficePassword();

    @Key("API_NEWS_HOST")
    String getApiNewsServiceUrl();

    @Key("API_CONTENT_HOST")
    String getApiContentServiceUrl();

    @Key("API_AUTH_HOST")
    String getAuthUrl();

    @Key("API_FILES_HOST")
    String getApiFilesServiceUrl();

    @Key("SELENOID_HUB_URL")
    String getSelenoidHubUrl();

    @Key("API_REVIEWS_HOST")
    String getApiReviewsService();

    @Key("API_BOOKMAKERS_HOST")
    String getBookmakersService();

    @Key("DB_USER")
    String getDBUser();

    @Key("DB_PASSWORD")
    String getDBPassword();

    @Key("DB_SERVER")
    String getDBServer();

    @Key("DB_NEWS_SERVICE_PORT")
    int getDBNewsServicePort();

    @Key("DB_REVIEWS_SERVICE_PORT")
    int getReviewsServicePort();

    @Key("browser.name")
    String getBrowserName();

    @Key("chrome.browser.version")
    String getChromeBrowserVersion();

}