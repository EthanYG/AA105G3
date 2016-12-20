package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ProductServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_no = null;
				try {
					prod_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件,存入req
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prod_no = new String(req.getParameter("prod_no"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(prod_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的productVO物件,存入req
				String url = "/back-end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_product_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prod_no = req.getParameter("prod_no").trim();
				String prod_name = req.getParameter("prod_name").trim();
				if(prod_name == ""){
					errorMsgs.add("請輸入商品名稱.");
				}
				
				String prod_type = req.getParameter("prod_type").trim();
				
				Integer sales_volume = null;
				try {
					sales_volume = new Integer(req.getParameter("sales_volume").trim());
				} catch (NumberFormatException e) {
					sales_volume = 0;
					errorMsgs.add("銷售數量請填數字.");
				}
				
				Integer stor_capacity = null;
				try {
					stor_capacity = new Integer(req.getParameter("stor_capacity").trim());
				} catch (NumberFormatException e) {
					stor_capacity = 0;
					errorMsgs.add("庫存數量請填數字.");
				}
				
				Integer unit_price = null;
				try {
					unit_price = new Integer(req.getParameter("unit_price").trim());
				} catch (NumberFormatException e) {
					unit_price = 100;
					errorMsgs.add("單價請填數字.");
				}
				
				String prod_description = req.getParameter("prod_description").trim();
				String prod_status = req.getParameter("prod_status").trim();
				String disc_status = req.getParameter("disc_status").trim();
				String sell_status = req.getParameter("sell_status").trim();
				
				ProductService productSvc = new ProductService();
				
				Part part = req.getPart("prod_picture");
				byte[] prod_picture = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					prod_picture = new byte[in.available()];
					in.read(prod_picture);
					in.close();
				}
				else{
					prod_picture = productSvc.getOneProduct(prod_no).getProd_picture();
				}
				
				java.sql.Date shelf_date = null;
				try {
					shelf_date = java.sql.Date.valueOf(req.getParameter("shelf_date").trim());
				} catch (IllegalArgumentException e) {
					shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}
				
				java.sql.Date remove_date = null;
				try {
					remove_date = java.sql.Date.valueOf(req.getParameter("remove_date").trim());
				} catch (IllegalArgumentException e) {
					remove_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下架日期!");
				}
				
				Integer disc_price = null;
				try {
					disc_price = new Integer(req.getParameter("disc_price").trim());
				} catch (NumberFormatException e) {
					disc_price = 100;
					errorMsgs.add("優惠價格請填數字.");
				}
				
				java.sql.Date disc_start_date = null;
				try {
					disc_start_date = java.sql.Date.valueOf(req.getParameter("disc_start_date").trim());
				} catch (IllegalArgumentException e) {
					disc_start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠起始日期!");
				}
				
				java.sql.Date disc_end_date = null;
				try {
					disc_end_date = java.sql.Date.valueOf(req.getParameter("disc_end_date").trim());
				} catch (IllegalArgumentException e) {
					disc_end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠結束日期!");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProd_no(prod_no);
				productVO.setProd_name(prod_name);
				productVO.setProd_type(prod_type);
				productVO.setSales_volume(sales_volume);
				productVO.setStor_capacity(stor_capacity);
				productVO.setUnit_price(unit_price);
				productVO.setProd_description(prod_description);
				productVO.setProd_status(prod_status);
				productVO.setDisc_status(disc_status);
				productVO.setSell_status(sell_status);
				productVO.setProd_picture(prod_picture);
				productVO.setShelf_date(shelf_date);
				productVO.setRemove_date(remove_date);
				productVO.setDisc_price(disc_price);
				productVO.setDisc_start_date(disc_start_date);
				productVO.setDisc_end_date(disc_end_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(prod_no, prod_name, prod_type, sales_volume, stor_capacity, 
						unit_price, prod_description, prod_status, disc_status, sell_status, prod_picture, 
						shelf_date, remove_date, disc_price, disc_start_date, disc_end_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的productVO物件,存入req
				String url = "/back-end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addProduct.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String prod_name = req.getParameter("prod_name").trim();
				if(prod_name == ""){
					errorMsgs.add("請輸入商品名稱.");
				}
				
				String prod_type = req.getParameter("prod_type").trim();
				
				Integer sales_volume = null;
				try {
					sales_volume = new Integer(req.getParameter("sales_volume").trim());
				} catch (NumberFormatException e) {
					sales_volume = 0;
					errorMsgs.add("銷售數量請填數字.");
				}
				
				Integer stor_capacity = null;
				try {
					stor_capacity = new Integer(req.getParameter("stor_capacity").trim());
				} catch (NumberFormatException e) {
					stor_capacity = 0;
					errorMsgs.add("庫存數量請填數字.");
				}
				
				Integer unit_price = null;
				try {
					unit_price = new Integer(req.getParameter("unit_price").trim());
				} catch (NumberFormatException e) {
					unit_price = 100;
					errorMsgs.add("單價請填數字.");
				}
				
				String prod_description = req.getParameter("prod_description").trim();
				String prod_status = req.getParameter("prod_status").trim();
				String disc_status = req.getParameter("disc_status").trim();
				String sell_status = req.getParameter("sell_status").trim();
				
				Part part = req.getPart("prod_picture");
				byte[] prod_picture = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					prod_picture = new byte[in.available()];
					in.read(prod_picture);
					in.close();
				}
				else{
					InputStream in = getServletContext().getResourceAsStream("/back-end/product/images/No-image-found.png");
					prod_picture = new byte[in.available()];
					in.read(prod_picture);
					in.close();
				}
				
				java.sql.Date shelf_date = null;
				try {
					shelf_date = java.sql.Date.valueOf(req.getParameter("shelf_date").trim());
				} catch (IllegalArgumentException e) {
					shelf_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}
				
				java.sql.Date remove_date = null;
				try {
					remove_date = java.sql.Date.valueOf(req.getParameter("remove_date").trim());
				} catch (IllegalArgumentException e) {
					remove_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下架日期!");
				}
				
				Integer disc_price = null;
				try {
					disc_price = new Integer(req.getParameter("disc_price").trim());
				} catch (NumberFormatException e) {
					disc_price = 100;
					errorMsgs.add("優惠價格請填數字.");
				}
				
				java.sql.Date disc_start_date = null;
				try {
					disc_start_date = java.sql.Date.valueOf(req.getParameter("disc_start_date").trim());
				} catch (IllegalArgumentException e) {
					disc_start_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠起始日期!");
				}
				
				java.sql.Date disc_end_date = null;
				try {
					disc_end_date = java.sql.Date.valueOf(req.getParameter("disc_end_date").trim());
				} catch (IllegalArgumentException e) {
					disc_end_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入優惠結束日期!");
				}

				ProductVO productVO = new ProductVO();
				productVO.setProd_name(prod_name);
				productVO.setProd_type(prod_type);
				productVO.setSales_volume(sales_volume);
				productVO.setStor_capacity(stor_capacity);
				productVO.setUnit_price(unit_price);
				productVO.setProd_description(prod_description);
				productVO.setProd_status(prod_status);
				productVO.setDisc_status(disc_status);
				productVO.setSell_status(sell_status);
				productVO.setProd_picture(prod_picture);
				productVO.setShelf_date(shelf_date);
				productVO.setRemove_date(remove_date);
				productVO.setDisc_price(disc_price);
				productVO.setDisc_start_date(disc_start_date);
				productVO.setDisc_end_date(disc_end_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(prod_name, prod_type, sales_volume, stor_capacity, 
						unit_price, prod_description, prod_status, disc_status, sell_status, prod_picture, 
						shelf_date, remove_date, disc_price, disc_start_date, disc_end_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}	
	}
	
	public String getFileNameFromPart(Part part){
		String header = part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	
}
