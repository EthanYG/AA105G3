<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.film.model.*"%>
<%
	FilmVO filmVO = (FilmVO) request.getAttribute("filmVO"); //FilmServlet.java (Concroller), 存入req的filmVO物件 (包括幫忙取出的filmVO, 也包括輸入資料錯誤時的filmVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_film_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_film_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="film.do" name="form1">
<table border="0">


	<tr>
		<td>影片編號:<font color=red><b>*</b></font></td>
		<td><%=filmVO.getFilm_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no"  size="45"  value="<%=filmVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>影片標題:</td>
		<td><input type="TEXT" name="film_title"  size="45"  value="<%=filmVO.getFilm_title()%>" /></td>
	</tr>
	<tr>
		<td>影片內容:</td>
		<td><input type="TEXT" name="film_content"  size="45"	  value="<%=filmVO.getFilm_content()%>" /></td>
	</tr>
	<tr>
		<td>按讚人數:</td>
		<td><input type="TEXT" name="film_like"  size="45"	value="<%=filmVO.getFilm_like()%>" /></td>
	</tr>
	<tr>
		<td>觀看人數:</td>
		<td><input type="TEXT" name="film_popular"  size="45"  value="<%=filmVO.getFilm_popular()%>" /></td>
	</tr>
		<td>影片上傳日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="film_date" value="<%=filmVO.getFilm_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','film_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	
	


	<jsp:useBean id="filmSvc" scope="page" class="com.film.model.FilmService" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="film_no" value="<%=filmVO.getFilm_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
