<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.collection.model.*"%>
<%
	CollectionVO collectionVO = (CollectionVO) request.getAttribute("collectionVO"); //CollectionServlet.java (Concroller), 存入req的collectionVO物件 (包括幫忙取出的collectionVO, 也包括輸入資料錯誤時的collectionVO物件)
%>
<html>
<head>
<title>我的最愛資料修改 - update_collection_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>我的最愛資料修改 - update_collection_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/collection/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="collection.do" name="form1">
<table border="0">
	<tr>
		<td>我的最愛編號:<font color=red><b>*</b></font></td>
		<td><%=collectionVO.getColl_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" value="<%=collectionVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>收藏目標編號:</td>
		<td><input type="TEXT" name="all_no" size="45"	value="<%=collectionVO.getAll_no()%>" /></td>
	</tr>
	<tr>
		<td>類別編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="class_no">
				<option value="R"  >食譜(R)
				<option value="F"  >影片(F)
				<option value="C"  >私廚(C)
				<option value="M"  >會員(M)
				<option value="L"  >實況主(L)
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="coll_no" value="<%=collectionVO.getColl_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
