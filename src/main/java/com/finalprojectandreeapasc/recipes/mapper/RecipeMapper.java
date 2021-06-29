package com.finalprojectandreeapasc.recipes.mapper;

import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeDto toDTO(Recipe recipe);
    Recipe fromDTO(RecipeDto recipe);

}
