package com.live.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.live.model.LiveService;
import com.live.model.LiveVO;
import com.live.model.*;

public class LiveServlet extends HttpServlet {

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
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/live/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
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
							.getRequestDispatcher("/front-end/live/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				LiveService liveSvc = new LiveService();
				LiveVO liveVO = liveSvc.getOneLive(mem_no);
				if (liveVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/live/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/************************ 3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("liveVO", liveVO); // 資料庫取出的liveVO物件,存入req
				String url = "/front-end/live/listOneLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //  成功轉交listOneLive.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/live/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //  來自listAllLive.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				LiveService liveSvc = new LiveService();
				LiveVO liveVO = liveSvc.getOneLive(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("liveVO", liveVO);         // 資料庫取出的liveVO物件,存入req
				String url = "/front-end/live/update_live_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_live_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/live/listAllLive.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { //  來自update_live_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String live_name = req.getParameter("live_name").trim();
				if(live_name  == "" ){
					errorMsgs.add("請輸入影片編號");
				}
				
				String live_intro = req.getParameter("live_intro").trim();
				if(live_intro  == ""){
					errorMsgs.add("請輸入會員編號");
				}
				

				Integer live_counts = null;
				try {
					live_counts = new Integer(req.getParameter("live_counts").trim());
				} catch (NumberFormatException e) {
					live_counts = 0;
					errorMsgs.add("請輸入按讚人數.");
				}
				Integer live_follow = null;
				try {
					live_follow = new Integer(req.getParameter("live_follow").trim());
				} catch (NumberFormatException e) {
					live_follow = 0;
					errorMsgs.add("請輸入按讚人數.");
				}
				


				String live_status = req.getParameter("live_status").trim();
				if(live_status  == ""){
					errorMsgs.add("請輸入會員編號");
				}
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no  == ""){
					errorMsgs.add("請輸入會員編號");
				}
				


				 
				
				LiveVO liveVO = new LiveVO();
				liveVO.setLive_name(live_name);
				liveVO.setLive_intro(live_intro);
				liveVO.setLive_counts(live_counts);
				liveVO.setLive_follow(live_follow);
				liveVO.setLive_status(live_status);
				liveVO.setMem_no(mem_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("liveVO", liveVO); //  含有輸入格式錯誤的liveVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/live/update_live_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LiveService liveSvc = new LiveService();
				liveVO = liveSvc.updateLive(live_name,live_intro,live_counts,live_follow,live_status,mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("liveVO", liveVO); // 資料庫update成功後,正確的的liveVO物件,存入req 
				String url = "/front-end/live/listOneLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneLive.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/live/update_live_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { //來自 addLive.jsp的請求
        	
        	

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("hahahahaha");
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = new String(req.getParameter("mem_no").trim());   
				
				String live_name = new String(req.getParameter("live_name").trim());	
				
				String live_intro = new String (req.getParameter("live_intro").trim());
				
				String live_status = new String (req.getParameter("live_status").trim());
				



				LiveVO liveVO = new LiveVO();
				liveVO.setMem_no(mem_no);
				liveVO.setLive_name(live_name);
				liveVO.setLive_intro(live_intro);
				liveVO.setLive_status(live_status);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("liveVO", liveVO); // 含有輸入格式錯誤的liveVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/live/addLive.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LiveService liveSvc = new LiveService();
				liveVO = liveSvc.addLive(mem_no, live_name , live_intro , live_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/live/listAllLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessage.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/live/addLive.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllLive.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始刪除資料***************************************/
				LiveService liveSvc = new LiveService();
				liveSvc.deleteLive(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/live/listAllLive.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//  刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/live/listAllLive.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
