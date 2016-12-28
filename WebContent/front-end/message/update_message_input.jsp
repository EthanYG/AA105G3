<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.message.model.*"%>
<%
	MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //MessageServlet.java (Concroller), 存入req的messageVO物件 (包括幫忙取出的messageVO, 也包括輸入資料錯誤時的messageVO物件)
%>


<%= messageVO.getMsg_no() %>
<html>
<head>
<title>留言內容修改 - update_message_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>  
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言內容修改 - update_message_input.jsp</h3>
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

<FORM METHOD="post" ACTION="message.do" name="form1">
<table border="0">

<!-- 	private static final String UPDATE = "UPDATE message set msg_detail = ?, msg_date=sysdate where msg_no= ? "; -->


	<tr>
		<td>留言編號:<font color=red><b>*</b></font></td>
		<td>${param.msg_no}</td>
	</tr>
	<tr>
		<td>留言內容:</td>
		<td><input type="TEXT" name="msg_detail"  size="45"  value="<%=messageVO.getMsg_detail()%>" /></td>
	</tr>

	
	


	<jsp:useBean id="messageSvc" scope="page" class="com.message.model.MessageService" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="msg_no" value="<%=messageVO.getMsg_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
