import api.Config;
import api.CreateUser;
import api.UserData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NegativeCreateUserTest extends Config {

    UserData userData;

    @Before
    public void createTestUser() {
        userData = new UserData();
    }

    @Test
    @DisplayName("Попытка создания пользователя без имени")
    public void createUserWithoutNameTest() {

        Response response = CreateUser.createUser(CreateUser.registerRequestBodyWithoutName(userData));

        String messageWithoutName = response
                .then()
                .assertThat()
                .statusCode(403)
                .and()
                .extract()
                .path("message");

        assertThat(messageWithoutName, equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Попытка создания пользователя без пароля")
    public void createUserWithoutPasswordTest() {

        Response response = CreateUser.createUser(CreateUser.registerRequestBodyWithoutPassword(userData));

        String messageWithoutPassword =
                response
                        .then()
                        .assertThat()
                        .statusCode(403)
                        .and()
                        .extract()
                        .path("message");
        assertThat(messageWithoutPassword, equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Попытка создания пользователя без почты")
    public void createUserWithoutEmailTest() {

        Response response = CreateUser.createUser(CreateUser.registerRequestBodyWithoutEmail(userData));
        String messageWithoutEmail = response
                .then()
                .assertThat()
                .statusCode(403)
                .and()
                .extract()
                .path("message");
        assertThat(messageWithoutEmail, equalTo("Email, password and name are required fields"));
    }
}