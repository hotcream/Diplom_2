import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest extends Config {

    UserData userData;

    @Before
    public void createTestUser() {
        userData = new UserData();
        CreateUser.createUserBeforeTests(userData);
    }

    @After
    public void deleteTestUser() {
        DeleteUser.deleteUserAfterTests();
    }

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginTest() {
        Response response = Login.login(CreateUser.registerRequestBody(userData));
        String token = response
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("accessToken");
        assertThat(token, notNullValue());
    }

    @Test
    @DisplayName("Попытка авторизации с неправильной почтой")
    public void loginWithBadLoginTest() {

        Response response = Login.login(Login.requestBodyLoginWithBadEmail(userData));
        String messageWithBadLogin = response
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");
        assertThat(messageWithBadLogin, equalTo("email or password are incorrect"));

    }

    @Test
    @DisplayName("Попытка авторизации с неправильным паролем")
    public void loginWithBadPasswordTest() {

        Response response = Login.login(Login.requestBodyLoginWithBadPassword(userData));
        String messageWithBadPassword = response
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(messageWithBadPassword, equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Попытка авторизации с неправильными почтой и паролем")
    public void loginWithBadEmailPasswordTest() {

        Response response = Login.login(Login.requestBodyLoginWithBadEmailAndPassword(userData));
        String messageWithBadEmailPassword = response
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(messageWithBadEmailPassword, equalTo("email or password are incorrect"));
    }
}