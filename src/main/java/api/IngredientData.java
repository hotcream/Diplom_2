package api;

import java.util.ArrayList;
import java.util.List;

public class IngredientData {

    private List<String> ingredients = new ArrayList<>();


    public IngredientData(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public IngredientData() {
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredient) {
        this.ingredients.add(ingredient);
    }
}
