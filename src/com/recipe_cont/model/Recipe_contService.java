package com.recipe_cont.model;

import java.util.List;
import java.util.Set;


public class Recipe_contService
{
	private Recipe_contDAO_interface dao;
	
	public Recipe_contService() {
		dao = new Recipe_contDAO();
	}
	
	public Recipe_contVO addRecipe_cont(String recipe_no, Integer step,byte[] step_pic,String step_cont) {

		Recipe_contVO recipe_contVO = new Recipe_contVO();

		recipe_contVO.setRecipe_no(recipe_no);
		recipe_contVO.setStep(step);
		recipe_contVO.setStep_pic(step_pic);
		recipe_contVO.setStep_cont(step_cont);
		dao.insert(recipe_contVO);

		return recipe_contVO;
	}
	
	public Recipe_contVO updateRecipe_cont(String recipe_no, Integer step,byte[] step_pic,String step_cont) {

		Recipe_contVO recipe_contVO = new Recipe_contVO();

		recipe_contVO.setRecipe_no(recipe_no);
		recipe_contVO.setStep(step);
		recipe_contVO.setStep_pic(step_pic);
		recipe_contVO.setStep_cont(step_cont);
		dao.update(recipe_contVO);

		return recipe_contVO;
	}
	public Recipe_contVO getOneRecipe_cont(String recipe_no, Integer step) {
		
		return dao.getOneCont(recipe_no, step);
	}
	
	public void deleteRecipe_cont(String recipe_no) {
		dao.delete(recipe_no);
	}
	
	public Set<Recipe_contVO> getRecipe_cont(String recipe_no) {
		return dao.findByPrimaryKey(recipe_no);
	}
	
	public List<Recipe_contVO> getAll() {
		return dao.getAll();
	}
}
