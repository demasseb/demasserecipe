package demasse.recipe.demasserecipe.controllers;

import demasse.recipe.demasserecipe.services.IngredientService;
import demasse.recipe.demasserecipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import demasse.recipe.demasserecipe.domain.Recipe;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model)
    {
        Recipe recipe= new Recipe();
        recipe.setId(new Long(id));
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
       model.addAttribute("ingredients", ingredientService.getIngredientByRecipe(recipe));

        return "/recipe/show";

    }
}
