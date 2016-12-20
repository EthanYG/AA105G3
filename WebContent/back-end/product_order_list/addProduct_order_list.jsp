<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order_list.model.*"%>
<%
Product_order_listVO product_order_listVO = (Product_order_listVO) request.getAttribute("product_order_listVO");
%>

<html>
<head>
<title>商品定明細資料新增 - addProduct_order_list.jsp</title></head>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品訂單明細資料新增 - addProduct_order_list.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/product_order_list/select_page.jsp">
		   <img src="<%=request.getContextPath()%>/back-end/product_order_list/images/back.png" width="70" height="70" border="0">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料商品訂單明細:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do" name="form1">
<table border="0">

	<tr>
		<td>訂單編號:</td>
		<td><input type="TEXT" name="prod_ord_no" size="45" 
			value="<%= (product_order_listVO==null)? "20160630-00000001" : product_order_listVO.getProd_ord_no()%>" /></td>
	</tr>

	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="prod_no" size="45" 
			value="<%= (product_order_listVO==null)? "1" : product_order_listVO.getProd_no()%>" /></td>
	</tr>

	<tr>
		<td>單價:</td>
		<td><input type="TEXT" name="unit_price" size="45"
			value="<%= (product_order_listVO==null)? "10" : product_order_listVO.getUnit_price()%>" /></td>
	</tr>
	<tr>
		<td>數量:</td>
		<td><input type="TEXT" name="prod_quantity" size="45"
			value="<%= (product_order_listVO==null)? "100" : product_order_listVO.getProd_quantity()%>" /></td>
	</tr>
	
	<tr>
		<td>訂單明細狀態:</td>
		<td><select size="1" name="deli_status">
				<option value="0" >未出貨
				<option value="1" >出貨中
				<option value="2" >已出貨
				<option value="3" >已退貨已退款
				<option value="4" >已退貨未退款
			</select></td>
	</tr>
	
	<tr>
		<td>商品出貨時間:</td>
		<td><input type="date" name="deli_time"></td>
	</tr>
	
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>