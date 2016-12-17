package com.recipe.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.recipe.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no");
				if (recipe_no == null || (recipe_no.trim()).length() == 0) {
					errorMsgs.add("請輸入食譜編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
				if (recipeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
				String url = "/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String recipe_no = req.getParameter("recipe_no");
				
				/***************************2.開始查詢資料****************************************/
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recipeVO", recipeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/recipe/update_recipe_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no").trim();
				String recipe_name= req.getParameter("recipe_name").trim();
				String recipe_intro= req.getParameter("recipe_intro");
				String food_mater= req.getParameter("food_mater").trim();
				
				if(recipe_name==null || recipe_name.isEmpty()){
					errorMsgs.add("食譜名稱不能為空白");
				}
				if(recipe_intro==null || recipe_intro.isEmpty()){
					errorMsgs.add("食譜簡介不能為空白");
				}
				if(food_mater==null || food_mater.isEmpty()){
					errorMsgs.add("食譜簡介不能為空白");
				}
				
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(recipe_no);	
				recipeVO.setRecipe_name(recipe_name);
				recipeVO.setRecipe_intro(recipe_intro);
				recipeVO.setFood_mater(food_mater);
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe/update_recipe_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RecipeService recipeSvc = new RecipeService();
				recipeVO = recipeSvc.updateRecipe(recipe_no, recipe_name, recipe_intro, food_mater);
				recipeVO = recipeSvc.getOneRecipe(recipe_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipeVO", recipeVO); 
				String url = "/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_recipe_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String recipe_name = req.getParameter("recipe_name").trim();
				String recipe_intro = req.getParameter("recipe_intro").trim();
				String food_mater = req.getParameter("food_mater");
				
				if(recipe_name==null || recipe_name.isEmpty()){
					errorMsgs.add("食譜名稱不能為空白");
				}
				if(recipe_intro==null || recipe_intro.isEmpty()){
					errorMsgs.add("食譜簡介不能為空白");
				}
				if(food_mater==null || food_mater.isEmpty()){
					errorMsgs.add("食材不能為空白");
				}
				
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setMem_no(mem_no);
				recipeVO.setRecipe_name(recipe_name);
				recipeVO.setRecipe_intro(recipe_intro);
				recipeVO.setFood_mater(food_mater);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe/addRecipe.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RecipeService recipeSvc = new RecipeService();
				recipeVO = recipeSvc.addRecipe(mem_no,recipe_name,recipe_intro,food_mater);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe/addRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}

}
