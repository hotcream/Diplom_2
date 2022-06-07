package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.ACCESS_TOKEN;
import static api.Base.REGISTER;
import static io.restassured.RestAssured.given;

public class CreateUser {

    static String userName = Base.createUserName();
    static String userPassword = Base.createPassword();
    static String userMail = Base.createMail();
    public static String userToken;

    @Step("Создание пользователя")
    public static Response createUser(String body) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(REGISTER);
    }

    @Step("Создание тела для регистрации")
    public static String registerRequestBody() {

        return "{\"name\":\"" + userName + "\","
                + "\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";
    }

    @Step("Получение JSON для запроса без пароля")
    public static String registerRequestBodyWithoutPassword() {

        return "{\"name\":\"" + userName + "\","
                + "\"email\":\"" + userMail + "\"}";
    }

    @Step("Получение JSON для запроса без имени")
    public static String registerRequestBodyWithoutName() {

        return "{\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";
    }

    @Step("Получение JSON для запроса без почты")
    public static String registerRequestBodyWithoutEmail() {

        return "{\"name\":\"" + userName + "\","
                + "\"password\":\"" + userPassword + "\"}";
    }

    public static void createUserBeforeTests() {
        createUser(registerRequestBody());
        userToken = Login.userToken();
    }
}