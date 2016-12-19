<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.collection.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CollectionService collectionSvc = new CollectionService();
    List<CollectionVO> list = collectionSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有我的最愛資料 - listAllCollection.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有我的最愛資料 - ListAllCollection.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/collection/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>我的最愛編號</th>
		<th>會員編號</th>
		<th>收藏目標編號</th>
		<th>類別編號</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="collectionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${collectionVO.coll_no}</td>
			<td>${collectionVO.mem_no}</td>
			<td>${collectionVO.all_no}</td>
			<td>${collectionVO.class_no}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/collection/collection.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coll_no" value="${collectionVO.coll_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/collection/collection.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="coll_no" value="${collectionVO.coll_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
