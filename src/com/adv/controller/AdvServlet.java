package com.adv.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adv.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class AdvServlet extends HttpServlet {
	
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
				String str = req.getParameter("adv_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adv/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String adv_no = null;
				try {
					adv_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adv/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdvService advSvc = new AdvService();
				AdvVO advVO = advSvc.getOneAdv(adv_no);
				if (advVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adv/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("advVO", advVO); // 資料庫取出的advVO物件,存入req
				String url = "/back-end/adv/listOneAdv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdv.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adv/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAdv.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String adv_no = new String(req.getParameter("adv_no"));
				
				/***************************2.開始查詢資料****************************************/
				AdvService advSvc = new AdvService();
				AdvVO advVO = advSvc.getOneAdv(adv_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("advVO", advVO);         // 資料庫取出的advVO物件,存入req
				String url = "/back-end/adv/update_adv_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_adv_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adv/listAllAdv.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_advber_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String adv_no = req.getParameter("adv_no").trim();
				if(adv_no == ""){
					errorMsgs.add("請輸入廣告編號.");
				}
				
				String emp_no = req.getParameter("emp_no").trim();
				if(emp_no == ""){
					errorMsgs.add("請輸入員工編號.");
				}
				
				String adv_name = req.getParameter("adv_name").trim();
				if(adv_name == ""){
					errorMsgs.add("請輸入廣告名稱.");
				}
				
				String adv_image_name = req.getParameter("adv_image_name").trim();
				if(adv_image_name == ""){
					errorMsgs.add("請輸入圖片名稱.");
				}
				
				AdvService advSvc = new AdvService();
				
				Part part = req.getPart("adv_image");
				byte[] adv_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					adv_image = new byte[in.available()];
					in.read(adv_image);
					in.close();
				}
				else{
					adv_image = advSvc.getOneAdv(adv_no).getAdv_image();
				}
				
				String adv_url = req.getParameter("adv_url").trim();
				if(adv_url == ""){
					errorMsgs.add("請輸入廣告連結.");
				}

				AdvVO advVO = new AdvVO();
				advVO.setAdv_no(adv_no);
				advVO.setEmp_no(emp_no);
				advVO.setAdv_name(adv_name);
				advVO.setAdv_image_name(adv_image_name);
				advVO.setAdv_image(adv_image);
				advVO.setAdv_url(adv_url);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advVO", advVO); // 含有輸入格式錯誤的advVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adv/update_adv_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//AdvService advSvc = new AdvService();
				advVO = advSvc.updateAdv(adv_no, emp_no, adv_name, adv_image_name, adv_image, adv_url);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("advVO", advVO); // 資料庫update成功後,正確的的advVO物件,存入req
				String url = "/back-end/adv/listOneAdv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdv.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adv/update_adv_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addAdv.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String emp_no = req.getParameter("emp_no").trim();
				if(emp_no == ""){
					errorMsgs.add("請輸入員工編號.");
				}
				
				String adv_name = req.getParameter("adv_name").trim();
				if(adv_name == ""){
					errorMsgs.add("請輸入廣告名稱.");
				}
				
				String adv_image_name = req.getParameter("adv_image_name").trim();
				if(adv_image_name == ""){
					errorMsgs.add("請輸入圖片名稱.");
				}
				
				Part part = req.getPart("adv_image");
				byte[] adv_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					adv_image = new byte[in.available()];
					in.read(adv_image);
					in.close();
				}
				else{
					InputStream in = getServletContext().getResourceAsStream("/back-end/adv/images/No-image-found.png");
					adv_image = new byte[in.available()];
					in.read(adv_image);
					in.close();
				}
				
				String adv_url = req.getParameter("adv_url").trim();
				if(adv_url == ""){
					errorMsgs.add("請輸入廣告連結.");
				}

				AdvVO advVO = new AdvVO();
				advVO.setEmp_no(emp_no);
				advVO.setAdv_name(adv_name);
				advVO.setAdv_image_name(adv_image_name);
				advVO.setAdv_image(adv_image);
				advVO.setAdv_url(adv_url);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advVO", advVO); // 含有輸入格式錯誤的advVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adv/addAdv.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdvService advSvc = new AdvService();
				advVO = advSvc.addAdv(emp_no, adv_name, adv_image_name, adv_image, adv_url);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/adv/listAllAdv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAdv.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/adv/addAdv.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("delete".equals(action)) { // 來自listAllAdv.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String adv_no = new String(req.getParameter("adv_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdvService advSvc = new AdvService();
				advSvc.deleteAdv(adv_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/adv/listAllAdv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adv/listAllAdv.jsp");
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
