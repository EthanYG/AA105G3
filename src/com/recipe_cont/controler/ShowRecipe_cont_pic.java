package com.recipe_cont.controler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe_cont.model.*;


@WebServlet("/recipe_cont/showRecipe_cont_pic.do")
public class ShowRecipe_cont_pic extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try
		{
			String recipe_no = req.getParameter("recipe_no");
			Integer step = new Integer(req.getParameter("step"));
			Recipe_contService recipe_contSvc = new Recipe_contService();
			Recipe_contVO recipe_contVO = recipe_contSvc.getOneRecipe_cont(recipe_no, step);
			
			
			byte[] buffer = recipe_contVO.getStep_pic();
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
