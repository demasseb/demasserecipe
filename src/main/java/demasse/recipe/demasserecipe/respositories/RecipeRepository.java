package demasse.recipe.demasserecipe.respositories;

import demasse.recipe.demasserecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
