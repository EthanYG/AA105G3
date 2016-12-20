<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.report_case.model.*"%>
<%
Report_caseVO report_caseVO = (Report_caseVO) request.getAttribute("report_caseVO");
%>

<html>
<head>
<title>檢舉案件新增 - addReport_case.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>檢舉案件資料新增 - addReport_case.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>檢舉案件資料:</h3>
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

<FORM METHOD="post" ACTION="report_case.do" name="form1">
<table border="0">
	
	<tr>
		<td>檢舉項目編號:</td>
		<td><input type="TEXT" name="rep_no" size="45" 
			value="<%= (report_caseVO==null)? "F00000001" : report_caseVO.getRep_no()%>" /></td>
	</tr>

	<tr>
		<td>檢舉會員編號:</td>
		<td><input type="TEXT" name="rep_mem_no" size="45" 
			value="<%= (report_caseVO==null)? "M00000001" : report_caseVO.getRep_mem_no()%>" /></td>
	</tr>
	<tr>
		<td>被檢舉案件編號:</td>
		<td><input type="TEXT" name="rep_tar_no" size="45" 
			value="<%= (report_caseVO==null)? "M00000001" : report_caseVO.getRep_tar_no()%>" /></td>
	</tr>
	<tr>
		<td>檢舉類別:</td>
		<td><input type="TEXT" name="rep_type" size="45"
			value="<%= (report_caseVO==null)? "" : report_caseVO.getRep_type()%>" /></td>
	</tr>
	<tr>
		<td>檢舉原因:</td>
		<td><input type="TEXT" name="rep_reason" size="45"
			value="<%= (report_caseVO==null)? "What's the reason?" : report_caseVO.getRep_reason()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>檢舉建立日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="rep_date" value="<%= (report_caseVO==null)? date_SQL : report_caseVO.getRep_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','hiredate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
		<tr>
		<td>檢舉審核狀態:</td>
		<td><input type="TEXT" name="rep_chk_con" size="45"
			value="<%= (report_caseVO==null)? "1" : report_caseVO.getRep_chk_con()%>" /></td>
	</tr>
		


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>