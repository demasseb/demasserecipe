package demasse.recipe.demasserecipe.services;

import demasse.recipe.demasserecipe.domain.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import demasse.recipe.demasserecipe.respositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet= new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }


    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional= recipeRepository.findById(l);
        if(!recipeOptional.isPresent())
        {
            throw new RuntimeException("Recipe not found");
        }
        return recipeOptional.get();
    }
}
