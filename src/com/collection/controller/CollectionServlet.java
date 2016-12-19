package com.collection.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collection.model.*;

public class CollectionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");System.out.println("action(CollectionServlet):"+ action);
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("coll_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入我的最愛編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String coll_no = null;
				try {
					coll_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CollectionService collectionSvc = new CollectionService();
				CollectionVO collectionVO = collectionSvc.getOneCollection(coll_no.toUpperCase());
				if (collectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("collectionVO", collectionVO); // 資料庫取出的collectionVO物件,存入req
				String url = "/front-end/collection/listOneCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCollection.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/select_page.jsp");
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
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
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
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CollectionService collectionSvc = new CollectionService();
				List collectionVO = collectionSvc.getAllByMem_noCollection(mem_no.toUpperCase());
				if (collectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("collectionVO", collectionVO); // 資料庫取出的collectionVO物件,存入req
				String url = "/front-end/collection/listAllByMem_noCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllByMem_noCollection.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
        if ("insert".equals(action)) { // 來自addCollection.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String all_no = req.getParameter("all_no").trim();System.out.println("all_no:"+all_no);
				String mem_no = req.getParameter("mem_no").trim();System.out.println("mem_no:"+mem_no);				
				String class_no = req.getParameter("class_no").trim();System.out.println("class_no:"+class_no);

				CollectionVO collectionVO = new CollectionVO();
				collectionVO.setMem_no(mem_no);
				collectionVO.setAll_no(all_no);
				collectionVO.setClass_no(class_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("collectionVO", collectionVO); // 含有輸入格式錯誤的collectionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/addCollection.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CollectionService collectionSvc = new CollectionService();
				collectionVO = collectionSvc.addCollection(mem_no.toUpperCase(), all_no.toUpperCase(), class_no.toUpperCase());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/collection/listAllCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/addCollection.jsp");
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
				String coll_no = new String(req.getParameter("coll_no"));
				
				/***************************2.開始刪除資料***************************************/
				CollectionService collectionSvc = new CollectionService();
				collectionSvc.deleteCollection(coll_no.toUpperCase());
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/collection/listAllCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/listAllCollection.jsp");
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
				String coll_no = new String(req.getParameter("coll_no"));
				
				/***************************2.開始查詢資料****************************************/
				CollectionService collectionSvc = new CollectionService();
				CollectionVO collectionVO = collectionSvc.getOneCollection(coll_no.toUpperCase());
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("collectionVO", collectionVO);         // 資料庫取出的collectionVO物件,存入req
				String url = "/front-end/collection/update_collection_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_collection_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/listAllCollection.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_collection_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String coll_no = new String(req.getParameter("coll_no").trim());
				String mem_no = req.getParameter("mem_no").trim();
				String all_no = req.getParameter("all_no").trim();
				String class_no = req.getParameter("class_no").trim();

				CollectionVO collectionVO = new CollectionVO();
				collectionVO.setColl_no(coll_no);
				collectionVO.setMem_no(mem_no);
				collectionVO.setAll_no(all_no);
				collectionVO.setClass_no(class_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("collectionVO", collectionVO); // 含有輸入格式錯誤的collectionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/collection/update_collection_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CollectionService collectionSvc = new CollectionService();
				collectionVO = collectionSvc.updateCollection(coll_no.toUpperCase(), mem_no.toUpperCase(), all_no.toUpperCase(), class_no.toUpperCase());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("collectionVO", collectionVO); // 資料庫update成功後,正確的的collectionVO物件,存入req
				String url = "/front-end/collection/listOneCollection.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/collection/update_collection_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
