<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Product_order_listService product_order_listSvc = new Product_order_listService();
    List<Product_order_listVO> list = product_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<html>
<head>
<title>所有商品訂單明細資料 - listAllProduct_order_list.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有商品訂單明細資料 - listAllProduct_order_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/product_order_list/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/product_order_list/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訂單編號</th>
		<th>商品名稱</th>
		<th>單價</th>
		<th>數量</th>
		<th>訂單明細狀態</th>
		<th>商品出貨時間</th>
		<th>修改</th>
	</tr>

	<c:forEach var="product_order_listVO" items="${list}">
		<tr align='center' valign='middle'}>
			<td>${product_order_listVO.prod_ord_no}</td>
			<td>
				<c:forEach var="productVO" items="${productSvc.all}">
                    <c:if test="${product_order_listVO.prod_no==productVO.prod_no}">
	                    ${productVO.prod_name}
                    </c:if>
                </c:forEach>
			</td>
			<td>${product_order_listVO.unit_price}</td>
			<td>${product_order_listVO.prod_quantity}</td>
			<td>
				<c:if test="${product_order_listVO.deli_status == '0'}" >
					未出貨
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '1'}" >
					出貨中
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '2'}" >
					已出貨
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '3'}" >
					已退貨已退款
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '4'}" >
					已退貨未退款
				</c:if>
			</td>
			<td>${product_order_listVO.deli_time}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prod_ord_no" value="${product_order_listVO.prod_ord_no}">
			     <input type="hidden" name="prod_no" value="${product_order_listVO.prod_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>