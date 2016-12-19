<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.customer_demand.model.*"%>
<%
Customer_demandVO customer_demandVO = (Customer_demandVO) request.getAttribute("customer_demandVO"); //Customer_demandServlet.java(Concroller), 存入req的customer_demandVO物件
%>
<html>
<head>
<title>客戶需求資料 - listOneCustomer_demand.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>客戶需求資料 - ListOneCustomer_demand.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end//customer_demand/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>客戶需求編號</th>
		<th>會員編號</th>
		<th>需求內容編號</th>
		<th>需求日期</th>
		<th>需求建立時間</th>
		<th>需求標題</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=customer_demandVO.getCusde_no()%></td>
		<td><%=customer_demandVO.getMem_no()%></td>
		<td><%=customer_demandVO.getCusde_detail()%></td>
		<td><%=customer_demandVO.getCusde_date()%></td>
		<td><%=customer_demandVO.getCusde_create_date()%></td>
		<td><%=customer_demandVO.getCusde_title()%></td>
	</tr>
</table>

</body>
</html>
