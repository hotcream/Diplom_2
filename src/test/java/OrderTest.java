import api.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class OrderTest extends Config {

    UserData userData;
    IngredientData ingredients;

    @Before
    public void createTestUser() {
        userData = new UserData();
        ingredients = new IngredientData();
        CreateUser.createUserBeforeTests(userData);
    }

    @After
    public void tearDown() {
        DeleteUser.deleteUserAfterTests();
    }

    @Test
    @DisplayName("Успешное создание заказа с авторизацией и ингредиентами")
    public void createPositiveOrder() {

        String name = Order
                .order(CreateUser.userToken, Order.positiveOrderBody(ingredients))
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
                .createOrderWithoutLogin(Order.positiveOrderBody(ingredients))
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredients() {
        String message = Order
                .order(CreateUser.userToken, Order.orderBodyWithoutIngredients(ingredients))
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
                .order(CreateUser.userToken, Order.negativeOrderBody(ingredients))
                .then()
                .assertThat()
                .statusCode(500);
    }
}