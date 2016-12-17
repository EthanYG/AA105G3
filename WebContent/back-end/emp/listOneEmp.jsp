<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.emp.model.*"%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>身分證字號</th>
		<th>E-mail</th>
		<th>住址</th>
		<th>手機</th>
		<th>進公司日期</th>
		<th>職位</th>
		<th>在職狀況</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${empVO.emp_no}</td>
			<td>${empVO.emp_name}</td>
			<td>${empVO.emp_account}</td>
			<td>${empVO.emp_password}</td>
			<td>${empVO.emp_id}</td>
			<td>${empVO.emp_email}</td>
			<td>${empVO.emp_address}</td>
			<td>${empVO.emp_phone}</td>
			<td>${empVO.emp_hiredate}</td>
			<td>${empVO.emp_job}</td>
			<td>${empVO.emp_status==1?"在職":"離職"}</td>
	</tr>
</table>

</body>
</html>
