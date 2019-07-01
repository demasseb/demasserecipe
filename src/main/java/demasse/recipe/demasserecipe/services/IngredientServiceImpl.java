package demasse.recipe.demasserecipe.services;

import org.springframework.stereotype.Service;
import demasse.recipe.demasserecipe.domain.Ingredient;
import demasse.recipe.demasserecipe.domain.Recipe;
import demasse.recipe.demasserecipe.respositories.IngredientRepository;

import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> getIngredientByRecipe(Recipe recipe) {

        Set<Ingredient> ingredient= ingredientRepository.findByRecipe(recipe);
        if (ingredient.isEmpty())
        {
            throw new RuntimeException("Recipe not found");
        }
        return ingredient;
    }
}
