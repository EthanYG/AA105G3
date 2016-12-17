<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.auth.model.*"%>
<%
AuthVO authVO = (AuthVO) request.getAttribute("authVO"); //AuthServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>權限資料 - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>權限資料 - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th>權限編號</th>
		<th>權限名稱</th>
		
	</tr>
	<tr align='center' valign='middle'>
		<td>${authVO.auth_no}</td>
			<td>${authVO.auth_name}</td>
			
	</tr>
</table>

</body>
</html>
