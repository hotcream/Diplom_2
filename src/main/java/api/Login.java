package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Login {

    static String userName = Base.createUserName();
    static String userPassword = Base.createPassword();
    static String userMail = Base.createMail();

    @Step("Авторизация")
    public static Response login(String body) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post("/api/auth/login");
    }

    @Step("Авторизация для получения токена")
    public static String userToken() {

        String token = Login.login(CreateUser.registerRequestBody())
                .then()
                .extract()
                .path("accessToken");
        return token.substring(7);
    }

    @Step("Тело запроса для авторизации c неправильным email")
    public static String requestBodyLoginWithBadEmail() {

        return "{\"email\":\"" + CreateUser.userName + "\","
                + "\"password\":\"" + CreateUser.userPassword + "\"}";
    }

    @Step("Тело запроса для авторизации c неправильным паролем")
    public static String requestBodyLoginWithBadPassword() {

        return "{\"email\":\"" + CreateUser.userMail + "\","
                + "\"password\":\"" + CreateUser.userName + "\"}";
    }

    @Step("Тело запроса для авторизации c неправильным логином и паролем")
    public static String requestBodyLoginWithBadEmailAndPassword() {

        return "{\"email\":\"" + CreateUser.userPassword + "\","
                + "\"password\":\"" + CreateUser.userName + "\"}";
    }

/*    @Step("Тело запроса регистрации пользователя")
    public static String testPositiveUser() {

        return "{\"password\":\"" + userPassword + "\","
                + "\"email\":\"" + userMail + "\"}";
    }*/

}