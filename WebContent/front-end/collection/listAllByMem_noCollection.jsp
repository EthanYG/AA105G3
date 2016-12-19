<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.collection.model.*"%>
<%@page import="java.util.*"%>
<%
List<CollectionVO> list = (List<CollectionVO>) request.getAttribute("collectionVO"); //CollectionServlet.java(Concroller), 存入req的collectionVO物件
pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>會員-我的最愛資料 - listAllByMem_noCollection.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員-我的最愛資料 - listAllByMem_noCollection.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/collection/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>我的最愛編號</th>
		<th>會員編號</th>
		<th>收藏目標編號</th>
		<th>類別編號</th>
	</tr>

	<c:forEach var="collectionVO" items="${list}" >
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

</body>
</html>
