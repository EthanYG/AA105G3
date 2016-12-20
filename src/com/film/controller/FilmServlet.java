package com.film.controller;


import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.film.model.FilmService;
import com.film.model.FilmVO;
import com.film.model.*;

public class FilmServlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action="+action);
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("film_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入影片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/film/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String film_no = null;
				try {
					film_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("影片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/film/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FilmService filmSvc = new FilmService();
				FilmVO filmVO = filmSvc.getOneFilm(film_no);
				if (filmVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/film/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("filmVO", filmVO); // ��Ʈw���X��filmVO����,�s�Jreq
				String url = "/front-end/film/listOneFilm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneFilm.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/film/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllFilm.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String film_no = new String(req.getParameter("film_no"));
				
				/***************************2.開始查詢資料****************************************/
				FilmService filmSvc = new FilmService();
				FilmVO filmVO = filmSvc.getOneFilm(film_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("filmVO", filmVO);         // ��Ʈw���X��filmVO����,�s�Jreq
				String url = "/front-end/film/update_film_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_film_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/film/listAllFilm.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { //  來自update_film_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String film_no = req.getParameter("film_no").trim();
				if(film_no  == "" ){
					errorMsgs.add("請輸入影片編號");
				}
				
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no  == ""){
					errorMsgs.add("請輸入會員編號");
				}
				
				
				String film_title = req.getParameter("film_title").trim();
				if(film_title  == "" ){
					errorMsgs.add("請輸入影片標題");
				}
								
				
				String film_content =  req.getParameter("film_content").trim();
				if(film_content  == "" ){
					errorMsgs.add("請輸入影片內容");
				}
				
				java.sql.Date film_date = null;
				try {
					film_date = java.sql.Date.valueOf(req.getParameter("film_date").trim());
				} catch (IllegalArgumentException e) {
					film_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入建立日期!");
				}
				
				Integer film_like = null;
				try {
					film_like = new Integer(req.getParameter("film_like").trim());
				} catch (NumberFormatException e) {
					film_like = 0;
					errorMsgs.add("請輸入按讚人數.");
				}
				
				Integer film_popular = null;
				try {
					film_popular = new Integer(req.getParameter("film_popular").trim());
				} catch (NumberFormatException e) {
					film_popular = 0;
					errorMsgs.add("請輸入觀看人數.");
				}



				 
				
				FilmVO filmVO = new FilmVO();
				filmVO.setFilm_no(film_no);
				filmVO.setMem_no(mem_no);
				filmVO.setFilm_title(film_title);
				filmVO.setFilm_content(film_content);
				filmVO.setFilm_date(film_date);
				filmVO.setFilm_like(film_like);
				filmVO.setFilm_popular(film_popular);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("filmVO", filmVO); //  含有輸入格式錯誤的filmVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/film/update_film_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FilmService filmSvc = new FilmService();
				filmVO = filmSvc.updateFilm(film_no,mem_no,film_title,film_content,film_date,film_like,film_popular);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("filmVO", filmVO); // 資料庫update成功後,正確的的filmVO物件,存入req 
				String url = "/front-end/film/listOneFilm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFilm.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/film/update_film_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { //來自 addFilm.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("hahahahaha");
			try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String film_no = new String(req.getParameter("film_no").trim());   
				String mem_no = new String(req.getParameter("mem_no").trim());				
				String film_title = new String (req.getParameter("film_title").trim());
				String film_content = new String (req.getParameter("film_content").trim());
				
				java.sql.Date film_date = null;
				try {
					film_date = java.sql.Date.valueOf(req.getParameter("film_date").trim());
				} catch (IllegalArgumentException e) {
					film_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入建立日期!");
				}
				
				Integer film_like = new Integer (req.getParameter("film_like").trim()); 
				Integer film_popular = new Integer (req.getParameter("film_popular").trim()); 


				FilmVO filmVO = new FilmVO();
				filmVO.setFilm_no(film_no);
				filmVO.setMem_no(mem_no);
				filmVO.setFilm_title(film_title);
				filmVO.setFilm_content(film_content);
				filmVO.setFilm_date(film_date);
				filmVO.setFilm_like(film_like);
				filmVO.setFilm_popular(film_popular);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("filmVO", filmVO); // 含有輸入格式錯誤的filmVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/film/addFilm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FilmService filmSvc = new FilmService();
				filmVO = filmSvc.addFilm(film_no, mem_no , film_title , film_content,film_date,film_like,film_popular);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/film/listAllFilm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMessage.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/film/addFilm.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllFilm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String film_no = new String(req.getParameter("film_no"));
				
				/***************************2.開始刪除資料***************************************/
				FilmService filmSvc = new FilmService();
				filmSvc.deleteFilm(film_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/film/listAllFilm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//  刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/film/listAllFilm.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
