<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_emp_input.jsp</h3>
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

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table border="0">
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td>${empVO.emp_no}</td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" size="45" value="<%=empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="emp_account" size="45" value="<%=empVO.getEmp_account()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="emp_password" size="45" value="<%=empVO.getEmp_password()%>" /></td>
	</tr>
	<tr>
		<td>身分證字號:</td>
		<td><input type="TEXT" name="emp_id" size="45" value="<%=empVO.getEmp_id()%>" /></td>
	</tr>
	<tr>
		<td>E-mail:</td>
		<td><input type="TEXT" name="emp_email" size="45" value="<%=empVO.getEmp_email()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="emp_address" size="45" value="<%=empVO.getEmp_address()%>" /></td>
	</tr>
	<tr>
		<td>聯絡電話:</td>
		<td><input type="TEXT" name="emp_phone" size="45" value="<%=empVO.getEmp_phone()%>" /></td>
	</tr>
	<tr>
		<td>雇用日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox" 
			type="date" name="emp_hiredate" value="<%=empVO.getEmp_hiredate()%>">
			
		</td>
	</tr>
	<tr>
		<td>職位:</td>
		<td><input type="TEXT" name="emp_job" size="45" value="<%=empVO.getEmp_job()%>" /></td>
	</tr>
	<tr>
		<td>在職狀態:</td>
		<td><select size="1" name="emp_status">
				<option value="0" ${(empVO.emp_status==0)?'selected':'' } >離職
				<option value="1" ${(empVO.emp_status==1)?'selected':'' } >在職
		</select></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="emp_no" value="<%=empVO.getEmp_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
