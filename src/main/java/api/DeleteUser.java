package api;

import io.qameta.allure.Step;

import static api.Base.USER;
import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Step("Удаление пользователя")
    public static void deleteUser(String token) {

        given()
                .auth().oauth2(token)
                .when()
                .delete(USER);
    }

    public static void deleteUserAfterTests() {
        DeleteUser.deleteUser(CreateUser.userToken);
    }
}