package com.recipe_cont.model;

public class Recipe_contVO
{
	private String recipe_no;
	private Integer step;
	private byte[] step_pic;
	private String step_cont;
	
	
	public String getRecipe_no()
	{
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no)
	{
		this.recipe_no = recipe_no;
	}
	public Integer getStep()
	{
		return step;
	}
	public void setStep(Integer step)
	{
		this.step = step;
	}
	public byte[] getStep_pic()
	{
		return step_pic;
	}
	public void setStep_pic(byte[] step_pic)
	{
		this.step_pic = step_pic;
	}
	public String getStep_cont()
	{
		return step_cont;
	}
	public void setStep_cont(String step_cont)
	{
		this.step_cont = step_cont;
	}
	
}
