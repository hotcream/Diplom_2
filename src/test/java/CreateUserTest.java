import api.Base;
import api.CreateUser;
import api.DeleteUser;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Base.BASE_URL;
    }

   @After
    public void deleteTestUser() {
        DeleteUser.deleteUserAfterTests();
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUserTest() {
        Response response = CreateUser.createUser(CreateUser.registerRequestBody());
        response
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Попытка создать пользователя, который уже зарегистрирован")
    public void createTwoSameUsersTest() {
        CreateUser.createUser(CreateUser.registerRequestBody());
        Response response = CreateUser.createUser(CreateUser.registerRequestBody());

        response
                .then()
                .assertThat()
                .statusCode(403)
                .and()
                .assertThat()
                .body("message", equalTo("User already exists"));
    }
}