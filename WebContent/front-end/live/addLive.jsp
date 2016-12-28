<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.live.model.*"%>
<%
LiveVO liveVO = (LiveVO) request.getAttribute("liveVO");
%>

<html>
<head>
<title>實況資料新增 - addLive.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>實況資料新增 - addLive.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>實況資料:</h3>
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
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (liveVO==null)? "M00000001" : liveVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>實況名稱:</td>
		<td><input type="TEXT" name="live_name" size="45"
			value="<%= (liveVO==null)? "I love It" : liveVO.getLive_name()%>" /></td>
	</tr>
	<tr>
		<td>實況簡介:</td>
		<td><input type="TEXT" name="live_intro" size="45"
			value="<%= (liveVO==null)? "100" : liveVO.getLive_intro()%>" /></td>
	</tr>
	
		<tr>
		<td>實況狀態:</td>
		<td><input type="TEXT" name="live_status" size="45"
			value="<%= (liveVO==null)? "1" : liveVO.getLive_status()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>