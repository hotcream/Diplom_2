package api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.LOGIN;
import static io.restassured.RestAssured.given;

public class Login {

    @Step("Авторизация")
    public static Response login(String json) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(LOGIN);
    }

    @Step("Авторизация для получения токена")
    public static String userToken(String json) {

        String token = Login.login(json)
                .then()
                .extract()
                .path("accessToken");

        return token.substring(7);
    }

    @Step("Тело запроса для авторизации c неправильным email")
    public static String requestBodyLoginWithBadEmail(UserData userData) {
        userData.setEmail("ttt@mail.ru");
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Тело запроса для авторизации c неправильным паролем")
    public static String requestBodyLoginWithBadPassword(UserData userData) {
        userData.setPassword("badPassword");
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Тело запроса для авторизации c неправильной почтой и паролем")
    public static String requestBodyLoginWithBadEmailAndPassword(UserData userData) {
        userData.setEmail("ttt@mail.ru");
        userData.setPassword("badPassword");
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

}