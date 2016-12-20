package com.recipe_cont.model;

import java.util.List;
import java.util.Set;

public interface Recipe_contDAO_interface
{
	public void insert(Recipe_contVO recipe_contVO);
    public void update(Recipe_contVO recipe_contVO);
    public void delete(String recipe_no);
    public void deleteOneStep(String recipe_no,Integer step);
    public Recipe_contVO getOneCont(String recipe_no,Integer step);
    public Set<Recipe_contVO> findByPrimaryKey(String recipe_no);
    public List<Recipe_contVO> getAll();
}
