package com.finalprojectandreeapasc.recipes.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    private Long id;
    private String title;
    private String ingredients;
    private Integer quantity;
    private Double price;
}
