import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserOrderListTest {

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
    @DisplayName("Список заказов неавторизованного пользователя")
    public void gatOrderWithoutAuthorization() {

        String message = UserOrderList
                .userOrderList("")
                .then()
                .assertThat()
                .statusCode(401)
                .and()
                .extract()
                .path("message");

        assertThat(message, equalTo("You should be authorised"));

    }

    @Test
    @DisplayName("Получение списка заказов у пользователя с авторизацией")
    public void getOrderWithAuthorization() {
        Order.order(Login.userToken(), Order.positiveOrderBody());
        ArrayList<LinkedHashMap> testBody = UserOrderList
                .userOrderList(Login.userToken())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("orders");

        assertThat(testBody.get(0), notNullValue());

    }
}