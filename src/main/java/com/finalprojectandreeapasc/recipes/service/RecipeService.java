package com.finalprojectandreeapasc.recipes.service;

import com.finalprojectandreeapasc.recipes.mapper.RecipeMapper;
import com.finalprojectandreeapasc.recipes.model.*;
import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import com.finalprojectandreeapasc.recipes.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    private Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found: " + id));
    }

    public List<RecipeDto> findAll() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RecipeDto create(RecipeDto item) {
        return recipeMapper.toDTO(recipeRepository.save(
                recipeMapper.fromDTO(item)
        ));
    }

    public RecipeDto update(Long id, RecipeDto recipe) {
        Recipe actRecipe = findById(id);
        actRecipe.setIngredients(recipe.getIngredients());
        actRecipe.setTitle(recipe.getTitle());
        actRecipe.setQuantity(recipe.getQuantity());
        return recipeMapper.toDTO(
                recipeRepository.save(actRecipe)
        );
    }

    public void delete(Long id){
        recipeRepository.deleteById(id);
    }

    public RecipeDto get(Long id) {
        return recipeMapper.toDTO(findById(id));
    }
}