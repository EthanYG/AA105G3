<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.frd_list.model.*"%>
<%@page import="java.util.*"%>
<%
List<Frd_listVO> list = (List<Frd_listVO>) request.getAttribute("frd_listVO"); //Frd_listServlet.java(Concroller), 存入req的frd_listVO物件
pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>會員-好友資料 - listAllByMem_noFrd_list.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員-好友資料 - listAllByMem_noFrd_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/frd_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>會員編號</th>
		<th>好友編號</th>
		<th>好友申請狀態</th>
	</tr>

	<c:forEach var="frd_listVO" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${frd_listVO.mem_no}</td>
			<td>${frd_listVO.friend_no}</td>
			<td>${frd_listVO.friend_chk}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frd_list/frd_list.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no" value="${frd_listVO.mem_no}">
			     <input type="hidden" name="friend_no" value="${frd_listVO.friend_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frd_list/frd_list.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="mem_no" value="${frd_listVO.mem_no}">
			    <input type="hidden" name="friend_no" value="${frd_listVO.friend_no}">
			    <input type="hidden" name="action"value="deleteOne"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
