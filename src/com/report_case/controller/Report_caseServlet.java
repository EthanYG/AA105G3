package com.report_case.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.report_case.model.Report_caseService;
import com.report_case.model.Report_caseVO;
import com.report_case.model.*;

public class Report_caseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action="+action);
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rep_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉項目編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String rep_no = null;
				try {
					rep_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉項目編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Report_caseService Report_caseSvc = new Report_caseService();
				Report_caseVO report_caseVO = Report_caseSvc.getOneReport_case(rep_no);
				if (report_caseVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("report_caseVO", report_caseVO); // 資料庫取出的report_caseVO物件,存入req
				String url = "/back-end/report_case/listOneReport_case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneReport_case.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report_case/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllReport_case.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String rep_no = new String(req.getParameter("rep_no"));
				
				/***************************2.開始查詢資料****************************************/
				Report_caseService Report_caseSvc = new Report_caseService();
				Report_caseVO report_caseVO = Report_caseSvc.getOneReport_case(rep_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("report_caseVO", report_caseVO);         // 資料庫取出的report_caseVO物件,存入req
				String url = "/back-end/report_case/update_report_case_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_report_case_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report_case/listAllReport_case.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_report_case_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		
				System.out.println("qwe");
				
				String rep_mem_no = req.getParameter("rep_mem_no").trim();
				if(rep_mem_no  == "" ){
					errorMsgs.add("請輸入檢舉會員編號");
				}
				
				String rep_tar_no = req.getParameter("rep_tar_no").trim();
				if(rep_tar_no  == ""){
					errorMsgs.add("請輸入檢舉案件編號");
				}
				
				
				String rep_type = req.getParameter("rep_type").trim();
				if(rep_type  == "" ){
					errorMsgs.add("請輸入檢舉類別");
				}
								
				
				String rep_reason =  req.getParameter("rep_reason").trim();
				if(rep_reason  == "" ){
					errorMsgs.add("請輸入檢舉原因");
				}
				
				java.sql.Date rep_date = null;
				try {
					rep_date = java.sql.Date.valueOf(req.getParameter("rep_date").trim());
				} catch (IllegalArgumentException e) {
					rep_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請檢舉建立日期!");
				}
				
				String rep_chk_con =  req.getParameter("rep_chk_con").trim();
				if(rep_chk_con  == "" ){
					errorMsgs.add("請輸入審核狀態");
				}
				

				


				 
				
				Report_caseVO report_caseVO = new Report_caseVO();
				report_caseVO.setRep_mem_no(rep_mem_no);
				report_caseVO.setRep_tar_no(rep_tar_no);
				report_caseVO.setRep_type(rep_type);
				report_caseVO.setRep_reason(rep_reason);
				report_caseVO.setRep_date(rep_date);
				report_caseVO.setRep_chk_con(rep_chk_con);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("report_caseVO", report_caseVO); // 含有輸入格式錯誤的report_caseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/update_report_case_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Report_caseService Report_caseSvc = new Report_caseService();
				report_caseVO = Report_caseSvc.updateReport_case(rep_mem_no,rep_tar_no,rep_type,rep_date,rep_reason,rep_chk_con);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("report_caseVO", report_caseVO); // 資料庫update成功後,正確的的report_caseVO物件,存入req
				String url = "/back-end/report_case/listOneReport_case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneReport_case.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report_case/update_report_case_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addReport_case.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("hahahahaha");
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
			
				
				
				
				
				String rep_mem_no =req.getParameter("rep_mem_no").trim();  
				if (rep_mem_no == null || (rep_mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String rep_tar_no =req.getParameter("rep_tar_no").trim();			
				if (rep_tar_no == null || (rep_tar_no.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉案件編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String rep_type =req.getParameter("rep_type").trim();
				if (rep_type == null || (rep_type.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉類別");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String rep_reason =req.getParameter("rep_reason").trim();
				
				java.sql.Date rep_date = null;
				try {
					rep_date = java.sql.Date.valueOf(req.getParameter("rep_date").trim());
				} catch (IllegalArgumentException e) {
					rep_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入建立日期!");
				}
				
				String rep_chk_con = new String (req.getParameter("rep_chk_con").trim());
				if (rep_chk_con == null || (rep_chk_con.trim()).length() == 0) {
					errorMsgs.add("請輸入審核狀態");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				Report_caseVO report_caseVO = new Report_caseVO();
				
				report_caseVO.setRep_mem_no(rep_mem_no);
				report_caseVO.setRep_tar_no(rep_tar_no);
				report_caseVO.setRep_type(rep_type);
				report_caseVO.setRep_reason(rep_reason);
				report_caseVO.setRep_date(rep_date);
				report_caseVO.setRep_chk_con(rep_chk_con);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("report_caseVO", report_caseVO); // 含有輸入格式錯誤的report_caseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report_case/addReport_case.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Report_caseService Report_caseSvc = new Report_caseService();
				System.out.println("asd");
				report_caseVO = Report_caseSvc.addReport_case(rep_mem_no,rep_tar_no,rep_type,rep_reason,rep_date,rep_chk_con);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/report_case/listAllReport_case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllReport_case.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report_case/addReport_case.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllReport_case.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String rep_no = new String(req.getParameter("rep_no"));
				
				/***************************2.開始刪除資料***************************************/
				Report_caseService Report_caseSvc = new Report_caseService();
				Report_caseSvc.deleteReport_case(rep_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/report_case/listAllReport_case.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report_case/listAllReport_case.jsp");
				failureView.forward(req, res);
			}
		}
	}
}