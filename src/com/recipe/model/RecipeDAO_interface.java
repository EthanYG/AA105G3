package com.recipe.model;

import java.util.List;

public interface RecipeDAO_interface
{
	public void insert(RecipeVO recipeVO);
    public void update(RecipeVO recipeVO);
    public void delete(String recipe_no);
    public RecipeVO findByPrimaryKey(String recipe_no);
    public List<RecipeVO> getAll();
    public void updateViews(RecipeVO recipeVO);
    public void updateLike(RecipeVO recipeVO);
    public void changeWeekViewsZero(String recipe_no);
    
}
