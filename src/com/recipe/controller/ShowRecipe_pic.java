package com.recipe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.model.*;


@WebServlet("/recipe/showRecipe_pic.do")
public class ShowRecipe_pic extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try
		{
			String recipe_no = req.getParameter("recipe_no");
			RecipeService recipeSvc = new RecipeService();
			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
			
			byte[] buffer = recipeVO.getRecipe_pic();
			out.write(buffer);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			InputStream in = getServletContext().getResourceAsStream("images/nopic.gif");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
		}
		
	}


}
