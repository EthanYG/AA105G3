package com.recipe_m_type.model;

import java.util.*;

public interface Recipe_m_typeDAO_interface {
	public void insert(Recipe_m_typeVO recipe_m_typeVO);
	public void update(Recipe_m_typeVO recipe_m_typeVO);
	public void delete(String recipe_m_type_no);
	public Recipe_m_typeVO findByPrimaryKey(String recipe_m_type_no);
	public List<Recipe_m_typeVO> getAll();

}
