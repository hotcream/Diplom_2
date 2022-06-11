import api.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends Config {

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
    @DisplayName("Изменение почты с авторизацией")
    public void updateMailWithAuthorizationTest() {

        boolean success = UpdateUser
                .patchUser(CreateUser.userToken, UpdateUser.testPositiveUserMail(userData))
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
                .patchUser(CreateUser.userToken, UpdateUser.testPositiveUserName(userData))
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
                .patchUser(CreateUser.userToken, UpdateUser.testPositiveUserPassword(userData))
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
                .patchUser("", UpdateUser.testPositiveUserPassword(userData))
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
                .patchUser("", UpdateUser.testPositiveUserMail(userData))
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
                .patchUser("", UpdateUser.testPositiveUserName(userData))
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(message, equalTo("You should be authorised"));
    }
}