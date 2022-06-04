package api;

import org.apache.commons.lang3.RandomStringUtils;

public class Base {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static String createMail() {
        return RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    }

    public static String createPassword() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String createUserName() {
        return RandomStringUtils.randomAlphabetic(8);
    }

}