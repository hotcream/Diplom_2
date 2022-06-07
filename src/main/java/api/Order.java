package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.ORDERS;
import static io.restassured.RestAssured.given;

public class Order {

    @Step("Создание заказа")
    public static Response order(String token, String body) {

        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .body(body)
                .when()
                .post(ORDERS);
    }


    @Step("Создание заказа без авторизации")
    public static Response createOrderWithoutLogin(String body) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(ORDERS);
    }
    @Step("Получение первого ингредиента")
    public static String firstIngredient() {
        return Ingredient
                .ingredient(CreateUser.userToken)
                .then()
                .extract()
                .path("data[0]._id");
    }

    @Step("Получение второго ингредиента")
    public static String secondIngredient() {
        return Ingredient
                .ingredient(CreateUser.userToken)
                .then()
                .extract()
                .path("data[1]._id");

    }

    @Step("Тело для позитивного запроса")
    public static String positiveOrderBody() {
        return "{\"ingredients\":" + "[" + "\"" + firstIngredient() + "\"" + ", " + "\"" + secondIngredient() + "\"" + "]" + "}";
    }

    @Step("Тело для негативного запроса")
    public static String negativeOrderBody() {
        return "{\"ingredients\":" + "[" + "\"" + firstIngredient() + "200" + "\"" + ", " + "\"" + secondIngredient() + "3000" + "\"" + "]" + "}";
    }

    @Step
    public static String orderBodyWithoutIngredients() {
        return "{\"ingredients\":" + "\"" + "\"" + "}";
    }

}