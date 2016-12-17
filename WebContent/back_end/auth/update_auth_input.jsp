<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.auth.model.*"%>
<%
	AuthVO authVO = (AuthVO) request.getAttribute("authVO"); //AuthServlet.java (Concroller), 存入req的authVO物件 
%>
<html>
<head>
<title>權限資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>權限資料修改 - update_auth_input.jsp</h3>
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

<FORM METHOD="post" ACTION="auth.do" name="form1">
<table border="0">
	<tr>
		<td>權限編號:<font color=red><b>*</b></font></td>
		<td><%=authVO.getAuth_no()%></td>
	</tr>
	<tr>
		<td>權限名稱:</td>
		<td><input type="TEXT" name="auth_name" size="45" value=${authVO.auth_name} /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="auth_no" value=${authVO.auth_no}>
<input type="submit" value="送出修改"></FORM>

</body>
</html>
