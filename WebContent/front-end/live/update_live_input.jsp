<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.live.model.*"%>
<%
	LiveVO liveVO = (LiveVO) request.getAttribute("liveVO"); //LiveServlet.java (Concroller), 存入req的liveVO物件 (包括幫忙取出的liveVO, 也包括輸入資料錯誤時的liveVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_live_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_live_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="live.do" name="form1">
<table border="0">


	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no"  size="45"  value="<%=liveVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>實況名稱:</td>
		<td><input type="TEXT" name="live_name"  size="45"  value="<%=liveVO.getLive_name()%>" /></td>
	</tr>
	<tr>
		<td>實況簡介:</td>
		<td><input type="TEXT" name="live_intro"  size="45"	  value="<%=liveVO.getLive_intro()%>" /></td>
	</tr>
	<tr>
		<td>實況總觀看次數:</td>
		<td><input type="TEXT" name="live_counts"  size="45"	value="<%=liveVO.getLive_counts()%>" /></td>
	</tr>
	<tr>
		<td>實況追隨人數:</td>
		<td><input type="TEXT" name="live_follow"  size="45"  value="<%=liveVO.getLive_follow()%>" /></td>
	</tr>
		<tr>
		<td>實況狀態:</td>
		<td><input type="TEXT" name="live_status"  size="45"  value="<%=liveVO.getLive_status()%>" /></td>
	</tr>
		
	
	


	<jsp:useBean id="liveSvc" scope="page" class="com.live.model.LiveService" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=liveVO.getMem_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
