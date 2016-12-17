package com.emp_auth.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.emp_auth.model.*;


public class Emp_authServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("listAuths_ByEmp_no".equals(action) || "getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_no = req.getParameter("emp_no");

				/*************************** 2.開始查詢資料 ****************************************/
				Emp_authService emp_authSvc = new Emp_authService();
				List<String> list = emp_authSvc.getAuthsStringByEmp_no(emp_no);
System.out.println(list);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAuths_ByEmp_no", list);    // 資料庫取出的set物件,存入request
				
				String url = null;
				if("listAuths_ByEmp_no".equals(action)){
					url = "/emp_auth/listAuths_ByEmp_no.jsp";        
				}else if("getOne_For_Update".equals(action)){
					url = "/emp_auth/update_emp_auth.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		
		if ("updateAuths_ByEmp_no".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String[] emp_auths = req.getParameterValues("emp_auths");
				String emp_no = req.getParameter("emp_no");
				
				if(emp_no==null||(emp_no.trim()).length()==0){
					errorMsgs.add("請輸入員工編號");
				}
				
				Emp_authVO emp_authVO = new Emp_authVO();
				Set set = new LinkedHashSet<Emp_authVO>();
				
				for(int i = 0;i<emp_auths.length;i++){
					
					emp_authVO.setEmp_no(emp_no);
					emp_authVO.setAuth_no(emp_auths[i]);
					set.add(emp_authVO);
				}
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emp_auths", set);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp_auth/update_emp_auth.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				
				/***************************2.開始修改資料*****************************************/
				
				Emp_authService emp_authSvc = new Emp_authService();
				
				emp_authSvc.deleteEmp_auth(emp_no);
				
				
				for(int i = 0;i<emp_auths.length;i++){
					
					emp_authVO = emp_authSvc.addEmp_Auth(emp_no,emp_auths[i]);
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("emp_auths", set); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp_auth/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp_auth/listAuths_ByEmp_no.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
