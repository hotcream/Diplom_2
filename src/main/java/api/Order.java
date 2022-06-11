package api;

import com.google.gson.Gson;
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

    @Step("Получение тела с ингредиентами для позитивного запроса")
    public static String positiveOrderBody(IngredientData ingredients) {
        ingredients.setIngredients(firstIngredient());
        ingredients.setIngredients(secondIngredient());
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }

    @Step("Получение тела с ингредиентами для негативного запроса")
    public static String negativeOrderBody(IngredientData ingredients) {
        ingredients.setIngredients(firstIngredient() + "200");
        ingredients.setIngredients(secondIngredient() + "3000");
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }

    @Step("Получение тела без ингредиентов")
    public static String orderBodyWithoutIngredients(IngredientData ingredients) {
        Gson gson = new Gson();
        return gson.toJson(ingredients);
    }
}