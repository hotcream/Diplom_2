import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static api.Login.userToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UpdateUserTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Base.BASE_URL;
    }

    @Before
    public void createTestUser() {
        CreateUser.createUserBeforeTests();
    }

    @After
    public void deleteTestUser() {
        DeleteUser.deleteUserAfterTests();
    }


    @Test
    @DisplayName("Изменение почты с авторизацией")
    public void updateMailWithAuthorizationTest() {

        boolean success = UpdateUser
                .patchUser(Login.userToken(), UpdateUser.testPositiveUserMail())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("success");

        assertThat(success, equalTo(true));

    }

    @Test
    @DisplayName("Изменение имени с авторизацией")
    public void updateNameWithAuthorizationTest() {

        boolean success = UpdateUser
                .patchUser(userToken(), UpdateUser.testPositiveUserName())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("success");

        assertThat(success, equalTo(true));

    }

    @Test
    @DisplayName("Изменение пароля с авторизацией")
    public void updatePasswordWithAuthorizationTest() {

        boolean success = UpdateUser
                .patchUser(userToken(), UpdateUser.testPositiveUserPassword())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("success");

        assertThat(success, equalTo(true));
    }

    @Test
    @DisplayName("Изменение пароля без авторизации")
    public void updatePasswordWithoutAuthorizationTest() {

        String message = UpdateUser
                .patchUser("", UpdateUser.testPositiveUserPassword())
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(message, equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Изменение почты без авторизации")
    public void updateEmailWithoutAuthorizationTest() {

        String message = UpdateUser
                .patchUser("", UpdateUser.testPositiveUserMail())
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(message, equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Изменение имени без авторизации")
    public void updateNameWithoutAuthorizationTest() {

        String message = UpdateUser
                .patchUser("", UpdateUser.testPositiveUserName())
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(message, equalTo("You should be authorised"));
    }
}