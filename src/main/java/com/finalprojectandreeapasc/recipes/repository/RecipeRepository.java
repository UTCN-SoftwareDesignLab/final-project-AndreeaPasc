package com.finalprojectandreeapasc.recipes.repository;

import com.finalprojectandreeapasc.recipes.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
