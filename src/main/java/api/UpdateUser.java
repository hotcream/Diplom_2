package api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

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
    public static String testPositiveUserName(UserData userData) {
        userData.setPassword(null);
        userData.setEmail(null);
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Тело для изменения email")
    public static String testPositiveUserMail(UserData userData) {
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Тело для изменения пароля")
    public static String testPositiveUserPassword(UserData userData) {
        Gson gson = new Gson();
        return gson.toJson(userData);
    }
}