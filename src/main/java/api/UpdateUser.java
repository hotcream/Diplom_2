package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.ACCESS_TOKEN;
import static api.Base.USER;
import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Step("Редактирование пользователя")
    public static Response patchUser(String token, String body) {

        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .body(body)
                .when()
                .patch(USER);
    }

    @Step("Тело для изменения имени")
    public static String testPositiveUserName() {

        return "{\"name\":\"" + CreateUser.userName + "\"}";
    }

    @Step("Тело для авторизации")
    public String bodyLogin() {

        return "{\"password\":\"" + CreateUser.userPassword + "\","
                + "\"email\":\"" + CreateUser.userMail + "\"}";
    }

 /*   @Step("Авторизация для получения токена")
    public static String userToken() {

        String token = Login
                .login(Login.userToken())
                .then()
                .extract()
                .path(ACCESS_TOKEN);

        return token.substring(7);

    }*/

    @Step("Тело для изменения email")
    public static String testPositiveUserMail() {

        return "{\"email\":\"" + CreateUser.userMail + "\"}";
    }


    @Step("Тело для изменения пароля")
    public static String testPositiveUserPassword() {

        return "{\"password\":\"" + CreateUser.userPassword + "\"}";
    }
}