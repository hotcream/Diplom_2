package api;

import io.restassured.RestAssured;
import org.junit.Before;

public class Config {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
}
