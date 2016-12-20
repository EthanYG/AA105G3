<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order_list.model.*"%>
<%
	Product_order_listVO product_order_listVO = (Product_order_listVO) request.getAttribute("product_order_listVO"); //Product_order_listServlet.java (Concroller), 存入req的product_order_listVO物件 (包括幫忙取出的product_order_listVO, 也包括輸入資料錯誤時的product_order_listVO物件)
%>
<html>
<head>
<title>商品訂單明細資料修改 - update_product_order_list_input.jsp</title></head>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品訂單明細資料修改 - update_product_order_list_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/product_order_list/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/product_order_list/images/back.png" width="70" height="70" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="product_order_list.do" name="form1">
<table border="0">
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=product_order_listVO.getProd_ord_no()%></td>
	</tr>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=product_order_listVO.getProd_no()%></td>
	</tr>
	<tr>
		<td>單價:<font color=red><b>*</b></font></td>
		<td><%=product_order_listVO.getUnit_price()%></td>
	</tr>
	<tr>
		<td>數量:<font color=red><b>*</b></font></td>
		<td><%=product_order_listVO.getProd_quantity()%></td>
	</tr>
	
	<jsp:useBean id="productSvc" scope="page" class="com.product_order_list.model.Product_order_listService" />
	<tr>
		<td>訂單明細狀態:</td>
		<td><select size="1" name="deli_status">
				<option value="0" <%= ((product_order_listVO.getDeli_status()).equals("0"))?"selected":"" %> >未出貨
				<option value="1" <%= ((product_order_listVO.getDeli_status()).equals("1"))?"selected":"" %> >出貨中
				<option value="2" <%= ((product_order_listVO.getDeli_status()).equals("2"))?"selected":"" %> >已出貨
				<option value="3" <%= ((product_order_listVO.getDeli_status()).equals("3"))?"selected":"" %> >已退貨已退款
				<option value="4" <%= ((product_order_listVO.getDeli_status()).equals("4"))?"selected":"" %> >已退貨未退款
			</select></td>
	</tr>
	
	<tr>
		<td>上架日期:</td>
		<td><input type="date" name="deli_time" value="<%=product_order_listVO.getDeli_time()%>"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_order_listVO.getProd_ord_no()%>">
<input type="hidden" name="prod_no" value="<%=product_order_listVO.getProd_no()%>">
<input type="hidden" name="unit_price" value="<%=product_order_listVO.getUnit_price()%>">
<input type="hidden" name="prod_quantity" value="<%=product_order_listVO.getProd_quantity()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>