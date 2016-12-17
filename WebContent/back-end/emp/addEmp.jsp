<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<! doctytype html>
<html>
<head>
<title>員工資料新增 - addEmp.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料新增 - addEmp.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1">
<table border="0">

	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" size="45" 
			value="<%= (empVO==null)? "新進員工" : empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="emp_account" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_account()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="emp_password" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_password()%>" /></td>
	</tr>
	<tr>
		<td>身分證字號:</td>
		<td><input type="TEXT" name="emp_id" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>E-mail:</td>
		<td><input type="TEXT" name="emp_email" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_email()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="emp_address" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_address()%>" /></td>
	</tr>
	<tr>
		<td>連絡電話:</td>
		<td><input type="TEXT" name="emp_phone" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_phone()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>雇用日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox" type="date" 
			  name="emp_hiredate" value="<%= (empVO==null)? date_SQL : empVO.getEmp_hiredate()%>">
			
		</td>
	</tr>
	<tr>
		<td>職位:</td>
		<td><input type="TEXT" name="emp_job" size="45"
			value="<%= (empVO==null)? "" : empVO.getEmp_job()%>" /></td>
	</tr>
	
	

	<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
	<%-- <tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr> --%>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
