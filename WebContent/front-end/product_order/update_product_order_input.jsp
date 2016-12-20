<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%
	Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO"); //Product_orderServlet.java (Concroller), 存入req的product_orderVO物件 (包括幫忙取出的product_orderVO, 也包括輸入資料錯誤時的product_orderVO物件)
%>
<html>
<head>
<title>商品訂單資料修改 - update_product_order_input.jsp</title></head>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品訂單資料修改 - update_product_order_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/product_order/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/product_order/images/back.png" width="70" height="70" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do" name="form1">
<table border="0">
	<tr>
		<td>訂單編號:</td>
		<td><%=product_orderVO.getProd_ord_no()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><%=product_orderVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>訂單成立時間:</td>
		<td><input type="date" name="prod_ord_time" value="<%=product_orderVO.getProd_ord_time()%>"></td>
	</tr>
	<tr>
		<td>信用卡卡號:</td>
		<td><input type="TEXT" name="cred_card_no" size="45"
			value="<%= (product_orderVO==null)? "A1B2C3D4E5F6G7H8" : product_orderVO.getCred_card_no()%>" /></td>
	</tr>
	<tr>
		<td>信用卡有效時期:</td>
		<td><input type="date" name="valid_date" value="<%=product_orderVO.getValid_date()%>"></td>
	</tr>
	<tr>
		<td>信用卡驗證碼:</td>
		<td><input type="TEXT" name="valid_no" size="45"
			value="<%= (product_orderVO==null)? "111" : product_orderVO.getValid_no()%>" /></td>
	</tr>
	<tr>
		<td>信用卡卡別:</td>
		<td><select size="1" name="cred_card_type">
				<option value="0" >VISA
				<option value="1" >MASTER
				<option value="2" >JCB
			</select></td>
	</tr>
	<tr>
		<td>訂單總金額:</td>
		<td><input type="TEXT" name="total_money" size="45"
			value="<%= (product_orderVO==null)? "10000" : product_orderVO.getTotal_money()%>" /></td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><input type="TEXT" name="ship_name" size="45"
			value="<%= (product_orderVO==null)? "地球人" : product_orderVO.getShip_name()%>" /></td>
	</tr>
	<tr>
		<td>郵遞區號:</td>
		<td><input type="TEXT" name="post_code" size="45"
			value="<%= (product_orderVO==null)? "32099" : product_orderVO.getPost_code()%>" /></td>
	</tr>
	<tr>
		<td>寄送地址:</td>
		<td><input type="TEXT" name="mem_adrs" size="45"
			value="<%= (product_orderVO==null)? "太陽系第三行星-地球" : product_orderVO.getMem_adrs()%>" /></td>
	</tr>
	<tr>
		<td>聯絡手機:</td>
		<td><input type="TEXT" name="cell_phone" size="45"
			value="<%= (product_orderVO==null)? "0987654321" : product_orderVO.getCell_phone()%>" /></td>
	</tr>
	<tr>
		<td>聯絡市話:</td>
		<td><input type="TEXT" name="tel_phone" size="45"
			value="<%= (product_orderVO==null)? "03-4567891" : product_orderVO.getTel_phone()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_orderVO.getProd_ord_no()%>">
<input type="hidden" name="mem_no" value="<%=product_orderVO.getMem_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>