<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.film.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    FilmService filmSvc = new FilmService();
    List<FilmVO> list = filmSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有員工資料 - listAllFilm.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllFilm.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>影片編號</th>
		<th>會員編號</th>
		<th>影片標題</th>
		<th>影片內容</th>
		<th>影片上傳時間</th>
		<th>儲存影片檔案</th>
		<th>影片讚數</th>
		<th>影片人氣</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="filmVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${filmVO.film_no}</td>
			<td>${filmVO.mem_no}</td>
			<td>${filmVO.film_title}</td>
			<td>${filmVO.film_content}</td>
			<td>${filmVO.film_date}</td>
			<td>${filmVO.film_file}</td>
			<td>${filmVO.film_like}</td>
			<td>${filmVO.film_popular}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/film/film.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="film_no" value="${filmVO.film_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/film/film.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="film_no" value="${filmVO.film_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
