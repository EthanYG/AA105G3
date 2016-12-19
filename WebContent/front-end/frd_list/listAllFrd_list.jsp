<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frd_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Frd_listService frd_listSvc = new Frd_listService();
    List<Frd_listVO> list = frd_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有好友資料 - listAllFrd_list.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有好友資料 - ListAllFrd_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/frd_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>會員編號</th>
		<th>好友編號</th>
		<th>好友申請狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="frd_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
<%@ include file="page2.file" %>

</body>
</html>
