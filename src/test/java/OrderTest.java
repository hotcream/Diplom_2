import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class OrderTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = Base.BASE_URL;
    }

    @Before
    public void createTestUser() {
        CreateUser.createUserBeforeTests();
    }

    @After
    public void tearDown() {
        DeleteUser.deleteUserAfterTests();
    }

    @Test
    @DisplayName("Успешное создание заказа с авторизацией и ингредиентами")
    public void createPositiveOrder() {

        String name = Order
                .order(Login.userToken(), Order.positiveOrderBody())
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .extract()
                .path("name");
        assertThat(name, containsString("бургер"));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithoutLogin() {
        Order
                .createOrderWithoutLogin(Order.positiveOrderBody())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {
        String message = Order
                .order(Login.userToken(), Order.orderBodyWithoutIngredients())
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .extract()
                .path("message");
        assertThat(message, equalTo("Ingredient ids must be provided"));
    }


    @Test
    @DisplayName("Успешное создание заказа с авторизацией и неверными ингредиентами")
    public void createOrderWithBadIngredients() {
        Order
                .order(Login.userToken(), Order.negativeOrderBody())
                .then()
                .assertThat()
                .statusCode(500);
    }
}