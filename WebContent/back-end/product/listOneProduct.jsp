<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>商品資料 - listOneProduct.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品資料 - listOneProduct.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/product/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品類別</th>
		<th>銷售數量</th>
		<th>庫存數量</th>
		<th>單價</th>
		<th>商品描述</th>
		<th>商品狀態</th>
		<th>優惠狀態</th>
		<th>銷售狀態</th>
		<th>商品圖片</th>
		<th>上架日期</th>
		<th>下架日期</th>
		<th>優惠價格</th>
		<th>優惠起始日期</th>
		<th>優惠結束日期</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${productVO.prod_no}</td>
		<td>${productVO.prod_name}</td>
		<td>
			<c:if test="${productVO.prod_type == 'SPACE BAG'}" >
				太空包
			</c:if>
			<c:if test="${productVO.prod_type == 'TABLEWARE'}" >
				餐具
			</c:if>
			<c:if test="${productVO.prod_type == 'KITCHENWARE'}" >
				廚具
			</c:if>
		</td>
		<td>${productVO.sales_volume}</td>
		<td>${productVO.stor_capacity}</td>
		<td>${productVO.unit_price}</td>
		<td>${productVO.prod_description}</td>
		<td>
			<c:if test="${productVO.prod_status == '0'}" >
				下架
			</c:if>
			<c:if test="${productVO.prod_status == '1'}" >
				上架
			</c:if>
			<c:if test="${productVO.prod_status == '2'}" >
				不再販售
			</c:if>
		</td>
		<td>${productVO.disc_status==0 ? '非特價' : '特價中'}</td>
		<td>${productVO.sell_status==0 ? '缺貨中' : '販售中'}</td>
		<td><img src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}" width='100'></td>
		<td>${productVO.shelf_date}</td>
		<td>${productVO.remove_date}</td>
		<td>${productVO.disc_price}</td>
		<td>${productVO.disc_start_date}</td>
		<td>${productVO.disc_end_date}</td>
	</tr>
</table>

</body>
</html>