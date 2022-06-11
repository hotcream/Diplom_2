package api;

import org.apache.commons.lang3.RandomStringUtils;

public class Base {

    public static final String INGREDIENTS = "/api/ingredients";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REGISTER = "/api/auth/register";
    public static final String USER = "/api/auth/user";
    public static final String LOGIN = "/api/auth/login";
    public static final String ORDERS = "/api/orders";


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