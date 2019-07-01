package demasse.recipe.demasserecipe.respositories;

import demasse.recipe.demasserecipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import demasse.recipe.demasserecipe.domain.Recipe;

import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {

    Set<Ingredient> findByRecipe(Recipe l);

}
