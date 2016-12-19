package com.customer_demand.controller;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer_demand.model.Customer_demandService;
import com.customer_demand.model.Customer_demandVO;

public class Customer_demandServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action:"+action);
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cusde_no");System.out.println("getOne_for_display str:"+str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入客戶需求編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String cusde_no = null;
				try {
					cusde_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				Customer_demandVO customer_demandVO = customer_demandSvc.getOneCustomer_demand(cusde_no.toUpperCase());
				if (customer_demandVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customer_demandVO", customer_demandVO); // 資料庫取出的customer_demandVO物件,存入req
				String url = "/front-end/customer_demand/listOneCustomer_demand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCustomer_demand.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getAllByMem_no_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				List customer_demandVO = customer_demandSvc.getAllByMem_noCustomer_demand(mem_no.toUpperCase());
				if (customer_demandVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customer_demandVO", customer_demandVO); // 資料庫取出的customer_demandVO物件,存入req
				String url = "/front-end/customer_demand/listAllByMem_noCustomer_demand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllByMem_noCustomer_demand.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addCustomer_demand.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String cusde_detail = req.getParameter("cusde_detail").trim();
				String mem_no = req.getParameter("mem_no").trim();				
				String cusde_title = req.getParameter("cusde_title").trim();
				
				java.sql.Timestamp cusde_date = null;
				try {
					cusde_date = java.sql.Timestamp.valueOf(req.getParameter("cusde_date").trim());
				} catch (IllegalArgumentException e) {
					cusde_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp cusde_create_date = null;
				try {
					cusde_create_date = java.sql.Timestamp.valueOf(req.getParameter("cusde_create_date").trim());
				} catch (IllegalArgumentException e) {
					cusde_create_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Customer_demandVO customer_demandVO = new Customer_demandVO();
				customer_demandVO.setMem_no(mem_no);
				customer_demandVO.setCusde_detail(cusde_detail);
				customer_demandVO.setCusde_title(cusde_title);
				customer_demandVO.setCusde_date(cusde_date);
				customer_demandVO.setCusde_create_date(cusde_create_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("customer_demandVO", customer_demandVO); // 含有輸入格式錯誤的customer_demandVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/addCustomer_demand.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				customer_demandVO = customer_demandSvc.addCustomer_demand(mem_no.toUpperCase(), cusde_detail, cusde_date, cusde_create_date, cusde_title);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/customer_demand/listAllCustomer_demand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/addCustomer_demand.jsp");
				failureView.forward(req, res);
			}
		}
        
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String cusde_no = new String(req.getParameter("cusde_no"));
				
				/***************************2.開始刪除資料***************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				customer_demandSvc.deleteCustomer_demand(cusde_no.toUpperCase());
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/customer_demand/listAllCustomer_demand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/listAllCustomer_demand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String cusde_no = new String(req.getParameter("cusde_no"));
				
				/***************************2.開始查詢資料****************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				Customer_demandVO customer_demandVO = customer_demandSvc.getOneCustomer_demand(cusde_no.toUpperCase());
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("customer_demandVO", customer_demandVO);         // 資料庫取出的customer_demandVO物件,存入req
				String url = "/front-end/customer_demand/update_customer_demand_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_customer_demand_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/listAllCustomer_demand.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_customer_demand_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String cusde_no = new String(req.getParameter("cusde_no").trim());
				String cusde_detail = req.getParameter("cusde_detail").trim();
				String mem_no = req.getParameter("mem_no").trim();				
				String cusde_title = req.getParameter("cusde_title").trim();
				
				java.sql.Timestamp cusde_date = null;
				try {
					cusde_date = java.sql.Timestamp.valueOf(req.getParameter("cusde_date").trim());
				} catch (IllegalArgumentException e) {
					cusde_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Timestamp cusde_create_date = null;
				try {
					cusde_create_date = java.sql.Timestamp.valueOf(req.getParameter("cusde_create_date").trim());
				} catch (IllegalArgumentException e) {
					cusde_create_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Customer_demandVO customer_demandVO = new Customer_demandVO();
				customer_demandVO.setCusde_no(cusde_no);
				customer_demandVO.setMem_no(mem_no);
				customer_demandVO.setCusde_detail(cusde_detail);
				customer_demandVO.setCusde_title(cusde_title);
				customer_demandVO.setCusde_date(cusde_date);
				customer_demandVO.setCusde_create_date(cusde_create_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("customer_demandVO", customer_demandVO); // 含有輸入格式錯誤的customer_demandVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/customer_demand/update_customer_demand_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Customer_demandService customer_demandSvc = new Customer_demandService();
				customer_demandVO = customer_demandSvc.updateCustomer_demand(cusde_no.toUpperCase(), mem_no.toUpperCase(), cusde_detail, cusde_date, cusde_create_date, cusde_title);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("customer_demandVO", customer_demandVO); // 資料庫update成功後,正確的的customer_demandVO物件,存入req
				String url = "/front-end/customer_demand/listOneCustomer_demand.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/customer_demand/update_customer_demand_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
	

}
