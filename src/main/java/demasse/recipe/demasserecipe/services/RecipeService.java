package demasse.recipe.demasserecipe.services;

import demasse.recipe.demasserecipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

   Recipe findById(Long l);
}
