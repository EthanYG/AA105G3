package com.recipe_type_info.model;

import java.util.List;

public interface Recipe_type_infoDAO_interface
{
	public void insert(Recipe_type_infoVO recipe_type_infoVO);
    public void update(Recipe_type_infoVO recipe_type_infoVO);
    public void delete(String recipe_no);
    public void deleteOneType(String recipe_no,String recipe_type_no);
    public List<Recipe_type_infoVO> findByPrimaryKey(String recipe_no);
    public List<Recipe_type_infoVO> getAll();
}
