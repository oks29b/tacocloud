package com.tacocloud.tacocloud.repository;

import com.tacocloud.tacocloud.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
