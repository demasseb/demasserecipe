package demasse.recipe.demasserecipe.services;

import demasse.recipe.demasserecipe.domain.Ingredient;
import demasse.recipe.demasserecipe.domain.Recipe;

import java.util.Set;

public interface IngredientService {

    Set<Ingredient> getIngredientByRecipe(Recipe recipe);
}
