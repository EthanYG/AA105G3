<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.auth.model.*"%>
<%
AuthVO authVO = (AuthVO) request.getAttribute("authVO");
%>

<html>
<head>
<title>權限新增頁面 - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>權限新增 - addAuth.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/auth/select_page.jsp">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料權限:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/auth/auth.do" name="form1">
<table border="0">

	<tr>
		<td>權限名稱:</td>
		<td><input type="TEXT" name="auth_name" size="45" placeholder="請輸入權限名稱"
			value="<%= (authVO==null)? "請輸入權限名稱" : authVO.getAuth_name()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
