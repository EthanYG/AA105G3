<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.frd_list.model.*"%>
<%
Frd_listVO frd_listVO = (Frd_listVO) request.getAttribute("frd_listVO"); //Frd_listServlet.java(Concroller), 存入req的frd_listVO物件
%>
<html>
<head>
<title>好友資料 - listOneFrd_list.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>好友資料 - ListOneFrd_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/frd_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>會員編號</th>
		<th>好友編號</th>
		<th>好友申請狀態</th>
	</tr>
	<tr align='center' valign='middle'>
			<td>${frd_listVO.mem_no}</td>
			<td>${frd_listVO.friend_no}</td>
			<td>${frd_listVO.friend_chk}</td>
	</tr>
</table>

</body>
</html>
