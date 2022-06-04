package api;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    @Step("Удаление пользователя")
    public static void deleteUser(String token) {

        given()
                .auth().oauth2(token)
                .when()
                .delete("/api/auth/user");
    }

    public static void deleteUserAfterTests() {
        String token = Login.login(CreateUser.registerRequestBody())
                .then()
                .extract()
                .path("accessToken");

        DeleteUser.deleteUser(token.substring(7));
    }
}