<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<jsp:useBean id="list" scope="session" type="java.util.List" />

<html>
<head>
<title>所有會員資料 - listAllMember2_getFromSession.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料共<font color=red><%=list.size()%></font>筆 - listAllMember2_getFromSession.jsp</h3>
		<a href="select_page.jsp"><img src="images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>會員編號</th>
		<th>會員暱稱</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員性別</th>
		<th>會員手機</th>
		<th>會員電子郵件</th>
		<th>會員地址</th>
		<th>會員資格</th>
		<th>會員影片觀看紀錄</th>
		<th>會員是否在線</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${memberVO.mem_no}</td>
			<td>${memberVO.mem_name}</td>
			<td>${memberVO.mem_ac}</td>
			<td>${memberVO.mem_pw}</td>
			<td>${memberVO.mem_sex}</td>
			<td>${memberVO.mem_phone}</td>
			<td>${memberVO.mem_email}</td>
			<td>${memberVO.mem_adrs}</td>
			<td>${memberVO.mem_own}</td>
			<td>${memberVO.mem_history}</td>
			<td>${memberVO.mem_online}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>