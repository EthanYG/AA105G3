package com.message.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.message.model.MessageService;
import com.message.model.MessageVO;
import com.message.model.*;

public class MessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action=" + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("msg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入主題留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String msg_no = null;
				try {
					msg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("主題留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MessageService MessageSvc = new MessageService();
				MessageVO messageVO = MessageSvc.getOneMessage(msg_no);
				if (messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("messageVO", messageVO); // 資料庫取出的messageVO物件,存入req
				String url = "/front-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMessage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String msg_no = new String(req.getParameter("msg_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				MessageService MessageSvc = new MessageService();
				MessageVO messageVO = MessageSvc.getOneMessage(msg_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("messageVO", messageVO); // 資料庫取出的messageVO物件,存入req
				String url = "/front-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_message_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_message_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				String msg_detail = req.getParameter("msg_detail").trim();
				if (msg_detail == "") {
					errorMsgs.add("請輸入留言內容");
				}

				String msg_no = req.getParameter("msg_no").trim();
				if (msg_no == "") {
					errorMsgs.add("請輸入檢舉案件編號");
				}

				MessageVO messageVO = new MessageVO();
				messageVO.setMsg_detail(msg_detail);
				messageVO.setMsg_no(msg_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的messageVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/message/update_message_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MessageService MessageSvc = new MessageService();
				messageVO = MessageSvc.updateMessage(msg_detail, msg_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("messageVO", messageVO); // 資料庫update成功後,正確的的messageVO物件,存入req
				String url = "/front-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/update_message_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addMessage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("hahahahaha");
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String mem_no = req.getParameter("mem_no").trim();
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String msg_mem_no = req.getParameter("msg_mem_no").trim();
				if (msg_mem_no == null || (msg_mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入欲留言對象之會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String msg_rel = req.getParameter("msg_rel").trim();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String msg_detail = req.getParameter("msg_detail").trim();
				if (msg_detail == null || (msg_detail.trim()).length() == 0) {
					errorMsgs.add("請輸入留言內容");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				java.sql.Date msg_date = null;
				try {
					msg_date = java.sql.Date.valueOf(req.getParameter("msg_date").trim());
				} catch (IllegalArgumentException e) {
					msg_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("留言建立日期!");
				}

				MessageVO messageVO = new MessageVO();

				messageVO.setMem_no(mem_no);
				messageVO.setMsg_mem_no(msg_mem_no);
				messageVO.setMsg_rel(msg_rel);
				messageVO.setMsg_detail(msg_detail);
				messageVO.setMsg_date(msg_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的messageVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MessageService MessageSvc = new MessageService();
				System.out.println("asd");
				messageVO = MessageSvc.addMessage(mem_no, msg_mem_no, msg_rel, msg_detail, msg_date);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/addMessage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllMessage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String msg_no = new String(req.getParameter("msg_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				MessageService MessageSvc = new MessageService();
				MessageSvc.deleteMessage(msg_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}