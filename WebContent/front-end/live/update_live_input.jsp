<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.live.model.*"%>
<%
	LiveVO liveVO = (LiveVO) request.getAttribute("liveVO"); //LiveServlet.java (Concroller), �s�Jreq��liveVO���� (�]�A�������X��liveVO, �]�]�A��J��ƿ��~�ɪ�liveVO����)
%>
<html>
<head>
<title>���u��ƭק� - update_live_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��ƭק� - update_live_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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
		<td><input type="TEXT" name="mem_no"  size="45"  value="<%=liveVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>��p�W��:</td>
		<td><input type="TEXT" name="live_name"  size="45"  value="<%=liveVO.getLive_name()%>" /></td>
	</tr>
	<tr>
		<td>��p²��:</td>
		<td><input type="TEXT" name="live_intro"  size="45"	  value="<%=liveVO.getLive_intro()%>" /></td>
	</tr>
	<tr>
		<td>��p�`�[�ݦ���:</td>
		<td><input type="TEXT" name="live_counts"  size="45"	value="<%=liveVO.getLive_counts()%>" /></td>
	</tr>
	<tr>
		<td>��p�l�H�H��:</td>
		<td><input type="TEXT" name="live_follow"  size="45"  value="<%=liveVO.getLive_follow()%>" /></td>
	</tr>
		<tr>
		<td>��p���A:</td>
		<td><input type="TEXT" name="live_status"  size="45"  value="<%=liveVO.getLive_status()%>" /></td>
	</tr>
		
	
	


	<jsp:useBean id="liveSvc" scope="page" class="com.live.model.LiveService" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=liveVO.getMem_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
