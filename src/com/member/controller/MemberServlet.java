package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MemberServlet extends HttpServlet {
	
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
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no == ""){
					errorMsgs.add("請輸入會員暱稱.");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name == ""){
					errorMsgs.add("請輸入會員暱稱.");
				}
				
				String mem_ac = req.getParameter("mem_ac").trim();
				if(mem_ac == ""){
					errorMsgs.add("請輸入會員帳號.");
				}
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw == ""){
					errorMsgs.add("請輸入會員密碼.");
				}
				
				MemberService memberSvc = new MemberService();
				
				Part part = req.getPart("mem_image");
				byte[] mem_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				else{
					mem_image = memberSvc.getOneMember(mem_no).getMem_image();
				}
				
				String mem_sex = null;
				try {
					mem_sex = new String(req.getParameter("mem_sex").trim());
				} catch (NullPointerException e) {
					mem_sex = "";
					errorMsgs.add("請選擇性別.");
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone == ""){
					errorMsgs.add("請輸入手機.");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email == ""){
					errorMsgs.add("請輸入電子郵件.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入地址.");
				}
				
				String mem_own = req.getParameter("mem_own").trim();
				String mem_history = req.getParameter("mem_history").trim();
				String mem_online = req.getParameter("mem_online").trim();

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_ac(mem_ac);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_image(mem_image);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_own(mem_own);
				memberVO.setMem_history(mem_history);
				memberVO.setMem_online(mem_online);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_no, mem_name, mem_ac, mem_pw, mem_image, mem_sex, mem_phone, mem_email, 
						mem_adrs, mem_own, mem_history, mem_online);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addMember.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name == ""){
					errorMsgs.add("請輸入會員暱稱.");
				}
				
				String mem_ac = req.getParameter("mem_ac").trim();
				if(mem_ac == ""){
					errorMsgs.add("請輸入會員帳號.");
				}
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw == ""){
					errorMsgs.add("請輸入會員密碼.");
				}
				
				Part part = req.getPart("mem_image");
				byte[] mem_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				else{
					InputStream in = getServletContext().getResourceAsStream("/front-end/member/images/No-image-found.png");
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				
				String mem_sex = null;
				try {
					mem_sex = new String(req.getParameter("mem_sex").trim());
				} catch (NullPointerException e) {
					mem_sex = "";
					errorMsgs.add("請選擇性別.");
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone == ""){
					errorMsgs.add("請輸入手機.");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email == ""){
					errorMsgs.add("請輸入電子郵件.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入地址.");
				}
				
				String mem_own = req.getParameter("mem_own").trim();
				String mem_history = req.getParameter("mem_history").trim();
				String mem_online = req.getParameter("mem_online").trim();

				MemberVO memberVO = new MemberVO();				
				memberVO.setMem_name(mem_name);
				memberVO.setMem_ac(mem_ac);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_own(mem_own);
				memberVO.setMem_history(mem_history);
				memberVO.setMem_online(mem_online);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mem_name, mem_ac, mem_pw, mem_image, mem_sex, mem_phone, mem_email, 
						mem_adrs, mem_own, mem_history, mem_online);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
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
