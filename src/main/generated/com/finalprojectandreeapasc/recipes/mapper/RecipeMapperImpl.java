package com.finalprojectandreeapasc.recipes.mapper;

import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-29T20:58:21+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class RecipeMapperImpl implements RecipeMapper {

    @Override
    public RecipeDto toDTO(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setId( recipe.getId() );
        recipeDto.setTitle( recipe.getTitle() );
        recipeDto.setIngredients( recipe.getIngredients() );
        recipeDto.setQuantity( recipe.getQuantity() );

        return recipeDto;
    }

    @Override
    public Recipe fromDTO(RecipeDto recipe) {
        if ( recipe == null ) {
            return null;
        }

        Recipe recipe1 = new Recipe();

        recipe1.setId( recipe.getId() );
        recipe1.setTitle( recipe.getTitle() );
        recipe1.setIngredients( recipe.getIngredients() );
        recipe1.setQuantity( recipe.getQuantity() );

        return recipe1;
    }
}
