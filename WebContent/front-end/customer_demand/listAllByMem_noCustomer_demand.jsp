<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.customer_demand.model.*"%>
<%@page import="java.util.*"%>
<%
List<Customer_demandVO> list = (List<Customer_demandVO>) request.getAttribute("customer_demandVO"); //Customer_demandServlet.java(Concroller), 存入req的customer_demandVO物件
pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>會員-客戶需求資料 - listAllByMem_noCustomer_demand.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員-客戶需求資料 - listAllByMem_noCustomer_demand.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end//customer_demand/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>客戶需求編號</th>
		<th>會員編號</th>
		<th>需求內容編號</th>
		<th>需求日期</th>
		<th>需求建立時間</th>
		<th>需求標題</th>
	</tr>

	<c:forEach var="customer_demandVO" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${customer_demandVO.cusde_no}</td>
			<td>${customer_demandVO.mem_no}</td>
			<td>${customer_demandVO.cusde_detail}</td>
			<td>${customer_demandVO.cusde_date}</td>
			<td>${customer_demandVO.cusde_create_date}</td>
			<td>${customer_demandVO.cusde_title}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer_demand/customer_demand.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="cusde_no" value="${customer_demandVO.cusde_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/customer_demand/customer_demand.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="cusde_no" value="${customer_demandVO.cusde_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
