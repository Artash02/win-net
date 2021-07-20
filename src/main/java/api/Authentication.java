package api;

import config.AppConfig;
import org.aeonbits.owner.ConfigFactory;
import rest.RequestMethods;

import java.io.IOException;

public class Authentication {

    public final String LOGIN_JSON = "src/test/resources/data/login/login.json";
    final AppConfig envConfig = ConfigFactory.create(AppConfig.class);
    final RequestMethods requestMethods = new RequestMethods();
    public static String token;

    public Authentication() throws IOException {
        token = requestMethods.getBearerToken(envConfig.getAuthUrl() + "/auth/login?rememberMe=true", LOGIN_JSON, 200);
    }
}
