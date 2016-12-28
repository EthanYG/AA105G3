<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.live.model.*"%>
<%
LiveVO liveVO = (LiveVO) request.getAttribute("liveVO");
%>

<html>
<head>
<title>��p��Ʒs�W - addLive.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��p��Ʒs�W - addLive.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>��p���:</h3>
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

<FORM METHOD="post" ACTION="live.do" name="form1">
<table border="0">
	


	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (liveVO==null)? "M00000001" : liveVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>��p�W��:</td>
		<td><input type="TEXT" name="live_name" size="45"
			value="<%= (liveVO==null)? "I love It" : liveVO.getLive_name()%>" /></td>
	</tr>
	<tr>
		<td>��p²��:</td>
		<td><input type="TEXT" name="live_intro" size="45"
			value="<%= (liveVO==null)? "100" : liveVO.getLive_intro()%>" /></td>
	</tr>
	
		<tr>
		<td>��p���A:</td>
		<td><input type="TEXT" name="live_status" size="45"
			value="<%= (liveVO==null)? "1" : liveVO.getLive_status()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>