package api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.REGISTER;
import static io.restassured.RestAssured.given;

public class CreateUser {

    public static String userToken;

    @Step("Создание пользователя")
    public static Response createUser(String json) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(REGISTER);

    }

    @Step("Создание тела для регистрации")
    public static String registerRequestBody(UserData userData) {
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Получение JSON для запроса без пароля")
    public static String registerRequestBodyWithoutPassword(UserData userData) {
        userData.setPassword(null);
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Получение JSON для запроса без имени")
    public static String registerRequestBodyWithoutName(UserData userData) {
        userData.setName(null);
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    @Step("Получение JSON для запроса без почты")
    public static String registerRequestBodyWithoutEmail(UserData userData) {
        userData.setEmail(null);
        Gson gson = new Gson();
        return gson.toJson(userData);
    }

    public static void createUserBeforeTests(UserData userData) {
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        createUser(registerRequestBody(userData));
        userToken = Login.userToken(json);

    }
}