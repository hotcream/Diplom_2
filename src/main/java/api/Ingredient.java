package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Base.INGREDIENTS;
import static io.restassured.RestAssured.given;

public class Ingredient {

    @Step("Получение ингредиентов")
    public static Response ingredient(String token) {

        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .when()
                .get(INGREDIENTS);
    }
}