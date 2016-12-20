package com.recipe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class RecipeService
{
	private RecipeDAO_interface dao;
		
	public RecipeService() {
		dao = new RecipeDAO();
	}
	public RecipeVO addRecipe(String mem_no,String recipe_name,String recipe_intro,String food_mater,byte[] recipe_pic) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMem_no(mem_no);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_intro(recipe_intro);
		recipeVO.setFood_mater(food_mater);
		recipeVO.setRecipe_pic(recipe_pic);
		dao.insert(recipeVO);

		return recipeVO;
	}

	public RecipeVO updateRecipe(String recipe_no,String recipe_name,String recipe_intro,String food_mater,byte[] recipe_pic) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_intro(recipe_intro);
		recipeVO.setFood_mater(food_mater);
		recipeVO.setRecipe_pic(recipe_pic);
		dao.update(recipeVO);

		return recipeVO;
	}

	public RecipeVO updateRecipeViews(String recipe_no ,Integer recipe_total_views,Integer recipe_week_views) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_total_views(recipe_total_views);
		recipeVO.setRecipe_week_views(recipe_week_views);
		dao.updateViews(recipeVO);

		return recipeVO;
	}
	
	public RecipeVO updateRecipeLike(String recipe_no ,Integer recipe_like) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_like(recipe_like);
		dao.updateLike(recipeVO);

		return recipeVO;
	}
	
	public void deleteRecipe(String recipe_no) {
		dao.delete(recipe_no);
	}

	public RecipeVO getOneRecipe(String recipe_no) {
		return dao.findByPrimaryKey(recipe_no);
	}

	public List<RecipeVO> getAll() {
		
		List<RecipeVO> list = dao.getAll();
		return list;
	}
	
	public void WeekViewsToZero(String recipe_no){
		
		dao.changeWeekViewsZero(recipe_no);
	}
}
