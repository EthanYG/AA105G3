<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product_order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>會員資料 - listOneProduct_order.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品訂單資料 - listOneProduct_order.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/product_order/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/product_order/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單成立時間</th>
		<th>信用卡卡號</th>
		<th>信用卡有效日期</th>
		<!-- <th>信用卡驗證碼</th> -->
		<th>信用卡卡別</th>
		<th>訂單總金額</th>
		<th>收件人姓名</th>
		<th>郵遞區號</th>
		<th>寄送地址</th>
		<th>聯絡手機</th>
		<th>聯絡市話</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${product_orderVO.prod_ord_no}</td>
		<td>${product_orderVO.mem_no}</td>
		<td>${product_orderVO.prod_ord_time}</td>
		<td>${product_orderVO.cred_card_no.subSequence(0,4)}********${product_orderVO.cred_card_no.subSequence(12,16)}</td>
		<td>${product_orderVO.valid_date}</td>
		<%-- <td>${product_orderVO.valid_no}</td> --%>
		<td>
			<c:if test="${product_orderVO.cred_card_type == '0'}" >
				VISA
			</c:if>
			<c:if test="${product_orderVO.cred_card_type == '1'}" >
				MASTER
			</c:if>
			<c:if test="${product_orderVO.cred_card_type == '2'}" >
				JCB
			</c:if>
		</td>
		<td>${product_orderVO.total_money}</td>
		<td>${product_orderVO.ship_name}</td>
		<td>${product_orderVO.post_code}</td>
		<td>${product_orderVO.mem_adrs}</td>
		<td>${product_orderVO.cell_phone}</td>
		<td>${product_orderVO.tel_phone}</td>
	</tr>
</table>

</body>
</html>