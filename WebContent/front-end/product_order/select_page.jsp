<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Product_order select</title></head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h2>分享食光</h2><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>測試商品訂單 (Product_order) 搜尋</p>

<h3>資料查詢:</h3>
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

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/product_order/listAllProduct_order.jsp'>List</a> all Product_orders. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do" >
        <b>輸入商品訂單編號 (如20160630-00000001):</b>
        <input type="text" name="prod_ord_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_orderService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do" >
       <b>選擇商品訂單編號:</b>
       <select size="1" name="prod_ord_no">
         <c:forEach var="product_orderVO" items="${product_orderSvc.all}" > 
          <option value="${product_orderVO.prod_ord_no}">${product_orderVO.prod_ord_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do" >
       <b>選擇會員:</b>
       <select size="1" name="prod_ord_no">
         <c:forEach var="product_orderVO" items="${product_orderSvc.all}" > 
          <option value="${product_orderVO.prod_ord_no}">${product_orderVO.mem_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>

<h3>商品訂單管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/product_order/addProduct_order.jsp'>Add</a> a new Product_order.</li>
</ul>

</body>

</html>