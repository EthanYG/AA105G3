<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java (Concroller), 存入req的memberVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>會員資料修改 - update_member_input.jsp</title></head>
<script language="JavaScript" src="<%=request.getContextPath()%>/front-end/member/js/member_image.js"></script>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料修改 - update_member_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/member/images/back.png" width="70" height="70" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>${memberVO.mem_no}</td>
		<%-- <td><%=memberVO.getMem_no()%></td> --%>
	</tr>
	<tr>
		<td>會員暱稱:</td>
		<td><input type="TEXT" name="mem_name" size="45" value="${memberVO.mem_name}" /></td>
		<%-- <td><input type="TEXT" name="mem_name" size="45" value="<%=memberVO.getMem_name()%>" /></td> --%>
	</tr>
	<tr>
		<td>帳號:<font color=red><b>*</b></font></td>
		<td>${memberVO.mem_ac}</td>
		<%-- <td><%=memberVO.getMem_ac()%></td> --%>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="PASSWORD" name="mem_pw" size="45" value="${memberVO.mem_pw}" /></td>
		<%-- <td><input type="TEXT" name="mem_pw" size="45" value="<%=memberVO.getMem_pw()%>" /></td> --%>
	</tr>
	<tr>
		<td>會員頭像:</td>
		<td><input accept="image/*" type="FILE" name="mem_image" id="mem_image" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="RADIO" name="mem_sex" value="0" ${memberVO.mem_sex=='0'?'checked':''} />女
			<input type="RADIO" name="mem_sex" value="1" ${memberVO.mem_sex=='1'?'checked':''} />男</td>
		<%-- <td><input type="RADIO" name="mem_sex" value="0" <%= (memberVO.getMem_sex().equals("0"))?"checked":"" %> />女
		<input type="RADIO" name="mem_sex" value="1" <%= (memberVO.getMem_sex().equals("1"))?"checked":"" %> />男</td> --%>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="mem_phone" size="45" value="${memberVO.mem_phone}" /></td>
		<%-- <td><input type="TEXT" name="mem_phone" size="45" value="<%=memberVO.getMem_phone()%>" /></td> --%>
	</tr>
	<tr>
		<td>電子郵件:</td>
		<td><input type="TEXT" name="mem_email" size="45" value="${memberVO.mem_email}" /></td>
		<%-- <td><input type="TEXT" name="mem_email" size="45" value="<%=memberVO.getMem_email()%>" /></td> --%>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="mem_adrs" size="45" value="${memberVO.mem_adrs}" /></td>
		<%-- <td><input type="TEXT" name="mem_adrs" size="45" value="<%=memberVO.getMem_adrs()%>" /></td> --%>
	</tr>
	<tr>
		<td>會員資格:</td>
		<td><input type="RADIO" name="mem_own" value="0" ${memberVO.mem_own=='0'?'checked':''} />一般會員
			<input type="RADIO" name="mem_own" value="1" ${memberVO.mem_own=='1'?'checked':''} />私廚</td>
		<%-- <td><input type="RADIO" name="mem_own" value="0" <%= ((memberVO.getMem_own()).equals("0"))?"checked":"" %> />一般會員
			<input type="RADIO" name="mem_own" value="1" <%= ((memberVO.getMem_own()).equals("1"))?"checked":"" %> />私廚</td> --%>
	</tr>
	<tr>
		<td>影片觀看紀錄:</td>
		<td><input type="TEXT" name="mem_history" size="45" value="${memberVO.mem_history}" /></td>
		<%-- <td><input type="TEXT" name="mem_history" size="45" value="<%=memberVO.getMem_history()%>" /></td> --%>
	</tr>
	<tr>
		<td>是否在線:</td>
		<td><input type="RADIO" name="mem_online" value="1" ${memberVO.mem_online=='0'?'checked':''} />在線
			<input type="RADIO" name="mem_online" value="0" ${memberVO.mem_online=='1'?'checked':''} />不在線</td>
		<%-- <td><input type="RADIO" name="mem_online" value="1" <%= ((memberVO.getMem_online()).equals("1"))?"checked":"" %> />在線
			<input type="RADIO" name="mem_online" value="0" <%= ((memberVO.getMem_online()).equals("0"))?"checked":"" %> />不在線</td> --%>
	</tr>
	
	<tr>
		<img id="img" src="/AA105G3/MemberDBGifReader.do?name=${memberVO.mem_no}" width='100'>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>">
<input type="hidden" name="mem_ac" value="<%=memberVO.getMem_ac()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>