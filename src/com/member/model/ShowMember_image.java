package com.member.model;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;

public class ShowMember_image extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try{
			
			String mem_no = req.getParameter("mem_no");
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(mem_no);
			
			byte[] buffer = memberVO.getMem_image();
			
		} catch(Exception e){
			
			InputStream in = getServletContext().getResourceAsStream("預設圖");
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			out.write(buffer);
			in.close();
			
		}
	
	}

}
