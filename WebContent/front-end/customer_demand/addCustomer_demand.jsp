<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.customer_demand.model.*"%>
<%
Customer_demandVO customer_demandVO = (Customer_demandVO) request.getAttribute("customer_demandVO");
%>

<html>
<head>
<title>我的最愛資料新增 - addCollection.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>我的資料新增 - addCollection.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end//customer_demand/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料我的最愛:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer_demand/customer_demand.do" name="form1">
<table border="0">

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (customer_demandVO==null)? "M00000001" : customer_demandVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>需求內容:</td>
		<td><input type="TEXT" name="cusde_detail" size="45"
			value="<%= (customer_demandVO==null)? "請輸入需求內容" : customer_demandVO.getCusde_detail()%>" /></td>
	</tr>
	<tr>
		<td>需求日期:</td>
		<td><input type="TEXT" name="cusde_date" size="45"
			value="<%= (customer_demandVO==null)? "請輸入時間格式" : customer_demandVO.getCusde_date()%>" /></td>
	</tr>
		<tr>
		<td>需求建立時間:</td>
		<td><input type="TEXT" name="cusde_create_date" size="45"
			value="<%= (customer_demandVO==null)? "請輸入時間格式" : customer_demandVO.getCusde_create_date()%>" /></td>
	</tr>
		<tr>
		<td>需求標題:</td>
		<td><input type="TEXT" name="cusde_title" size="45"
			value="<%= (customer_demandVO==null)? "請輸入標題" : customer_demandVO.getCusde_title()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
