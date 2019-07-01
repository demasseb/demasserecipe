package demasse.recipe.demasserecipe.bootstrap;

import demasse.recipe.demasserecipe.domain.*;
import demasse.recipe.demasserecipe.respositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import demasse.recipe.demasserecipe.domain.Recipe;
import demasse.recipe.demasserecipe.respositories.CategoryRepository;
import demasse.recipe.demasserecipe.respositories.RecipeRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipe() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("Ounce");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }


        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pinch");

        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUom("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUOM = teaSpoonUomOptional.get();
        UnitOfMeasure pintuom = pintUomOptional.get();
        UnitOfMeasure cupuom = cupUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(10);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirection("Na so it is done");

        Notes guacNotes = new Notes();


        guacRecipe.setNotes(guacNotes);
        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom,guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Naso", new BigDecimal(2), eachUom,guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);


        Recipe tacRecipe = new Recipe();
        tacRecipe.setDescription("Perfect Tacos");
        tacRecipe.setPrepTime(10);
        tacRecipe.setCookTime(10);
        tacRecipe.setDifficulty(Difficulty.MODERATE);
        tacRecipe.setDirection("Nor be so dear");

        Notes tacNotes = new Notes();


        tacRecipe.setNotes(tacNotes);
        tacRecipe.getIngredients().add(new Ingredient("ripe dude", new BigDecimal(2), eachUom,tacRecipe));
        tacRecipe.getIngredients().add(new Ingredient("Naso bighead", new BigDecimal(2), eachUom,tacRecipe));

        tacRecipe.getCategories().add(americanCategory);
        tacRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacRecipe);

        return  recipes;

    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipe());
    }
}
