<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO");
%>

<html>
<head>
<title>�s�W�d�� - addMessage.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d����Ʒs�W - addMessage.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>���|�ץ���:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	 <ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="message.do" name="form1">
<table border="0">

	
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (messageVO==null)? "M00000001" : messageVO.getMem_no()%>" /></td>
	</tr>

	<tr>
		<td>�d����H�|���s��:</td>
		<td><input type="TEXT" name="msg_mem_no" size="45" 
			value="<%= (messageVO==null)? "M00000001" : messageVO.getMsg_mem_no()%>" /></td>
	</tr>
	<tr>
		<td>�����d���s��:</td>
		<td><input type="TEXT" name="msg_rel" size="45" 
			placeholder="<%= (messageVO==null)? "ME00000001" : messageVO.getMsg_rel()%>" /></td>
	</tr>
	<tr>
		<td>�d�����e:</td>
		<td><input type="TEXT" name="msg_detail" size="45"
			value="<%= (messageVO==null)? "" : messageVO.getMsg_detail()%>" /></td>
	</tr>
	<tr>

	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�d���إߤ��:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="msg_date" value="<%= (messageVO==null)? date_SQL : messageVO.getMsg_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','msg_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		</td>
	</tr>



</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>