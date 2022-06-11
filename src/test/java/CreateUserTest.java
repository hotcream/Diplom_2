import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends Config {

    @After
    public void deleteTestUser() {
        DeleteUser.deleteUserAfterTests();
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUserTest() {
        UserData userData = new UserData();
        Response response = CreateUser.createUser(CreateUser.registerRequestBody(userData));
        CreateUser.userToken = Login.userToken(CreateUser.registerRequestBody(userData));
        response
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Попытка создать пользователя, который уже зарегистрирован")
    public void createTwoSameUsersTest() {
        UserData userData = new UserData();
        CreateUser.createUser(CreateUser.registerRequestBody(userData));
        Response response = CreateUser.createUser(CreateUser.registerRequestBody(userData));
        CreateUser.userToken = Login.userToken(CreateUser.registerRequestBody(userData));
        response
                .then()
                .assertThat()
                .statusCode(403)
                .and()
                .assertThat()
                .body("message", equalTo("User already exists"));
    }
}