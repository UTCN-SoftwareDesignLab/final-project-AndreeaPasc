package com.finalprojectandreeapasc.recipes.controller;
import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import com.finalprojectandreeapasc.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finalprojectandreeapasc.UrlMapping.ENTITY;
import static com.finalprojectandreeapasc.UrlMapping.RECIPES;

@RestController
@RequestMapping(RECIPES)
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> allRecipes() {
        return recipeService.findAll();
    }

    @PostMapping
    public RecipeDto create(@RequestBody RecipeDto recipe){
        return recipeService.create(recipe);
    }

    @PutMapping(ENTITY)
    public RecipeDto update(@PathVariable Long id, @RequestBody RecipeDto recipe) {
        return recipeService.update(id, recipe);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        recipeService.delete(id);
    }

    @GetMapping(ENTITY)
    public RecipeDto getRecipe(@PathVariable Long id) {
        return recipeService.get(id);
    }
}
