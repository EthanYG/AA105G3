<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<title>商品資料新增 - addProduct.jsp</title></head>
<script language="JavaScript" src="<%=request.getContextPath()%>/back-end/product/js/product_picture.js"></script>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品資料新增 - addProduct.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp">
		   <img src="<%=request.getContextPath()%>/back-end/product/images/back.png" width="70" height="70" border="0">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料商品:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="prod_name" size="45" 
			value="<%= (productVO==null)? "新的商品" : productVO.getProd_name()%>" /></td>
	</tr>
	
	<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
	<tr>
		<td>商品類別:</td>
		<td><select size="1" name="prod_type">
				<option value="SPACE BAG" >太空包
				<option value="TABLEWARE" >餐具
				<option value="KITCHENWARE" >廚具
			</select></td>
	</tr>
	
	<tr>
		<td>銷售數量:</td>
		<td><input type="TEXT" name="sales_volume" size="45"
			value="<%= (productVO==null)? "10" : productVO.getSales_volume()%>" /></td>
	</tr>
	<tr>
		<td>庫存數量:</td>
		<td><input type="TEXT" name="stor_capacity" size="45"
			value="<%= (productVO==null)? "100" : productVO.getStor_capacity()%>" /></td>
	</tr>
	<tr>
		<td>單價:</td>
		<td><input type="TEXT" name="unit_price" size="45"
			value="<%= (productVO==null)? "50" : productVO.getUnit_price()%>" /></td>
	</tr>
	<tr>
		<td>商品描述:</td>
		<td><textarea name="prod_description" cols="50" rows="5">
		<%= (productVO==null)? "最新的太空包上市！" : productVO.getProd_description()%></textarea></td>
	</tr>
	
	<tr>
		<td>商品狀態:</td>
		<td><select size="1" name="prod_status">
				<option value="0" >下架
				<option value="1" >上架
				<option value="2" >不再販售
			</select></td>
	</tr>
	
	<tr>
		<td>優惠狀態:</td>
		<td><select size="1" name="disc_status">
				<option value="0" >非特價
				<option value="1" >特價中
			</select></td>
	</tr>
	<tr>
		<td>銷售狀態:</td>
		<td><select size="1" name="sell_status">
				<option value="0" >缺貨中
				<option value="1" >販售中
			</select></td>
	</tr>
	<tr>
		<td>商品圖片:</td>
		<td><input accept="image/*" type="FILE" name="prod_picture" id="prod_picture" /></td>
	</tr>
	<tr>
		<td>上架日期:</td>
		<td><input type="date" name="shelf_date"></td>
	</tr>
	
	<tr>
		<td>下架日期:</td>
		<td><input type="date" name="remove_date"></td>
	</tr>

	<tr>
		<td>優惠價格:</td>
		<td><input type="TEXT" name="disc_price" size="45"
			value="<%= (productVO==null)? "25" : productVO.getDisc_price()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠起始日期:</td>
		<td><input type="date" name="disc_start_date"></td>
	</tr>
	
	<tr>
		<td>優惠結束日期:</td>
		<td><input type="date" name="disc_end_date"></td>
	</tr>
	
	<tr>
		<img id="img" src="<%=request.getContextPath()%>/back-end/product/images/No-image-found.png" width='100'>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>