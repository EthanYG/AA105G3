<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.frd_list.model.*"%>
<%
	Frd_listVO frd_listVO = (Frd_listVO) request.getAttribute("frd_listVO"); //Frd_listServlet.java (Concroller), 存入req的frd_listVO物件 (包括幫忙取出的frd_listVO, 也包括輸入資料錯誤時的frd_listVO物件)
%>
<html>
<head>
<title>好友資料修改 - update_frd_list_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>好友資料修改 - update_frd_list_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/frd_list/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frd_list/frd_list.do" name="form1">
<table border="0">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=frd_listVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>好友編號:</td>
		<td><input type="TEXT" name="friend_no" size="45" value="<%=frd_listVO.getFriend_no()%>" /></td>
	</tr>
	<tr>
		<td>好友申請況態:<font color=red><b>*</b></font></td>
		<td><select size="1" name="friend_chk">
				<option value="0"  >未申請(0)
				<option value="1"  >已申請(1)
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=frd_listVO.getMem_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
