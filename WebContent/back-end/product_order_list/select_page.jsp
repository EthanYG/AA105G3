<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Product_order_list select</title></head>
<body>

<table border='1' cellpadding='3' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h2>分享食光</h2><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>測試商品訂單明細 (Product_order_list) 搜尋</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/product_order_list/listAllProduct_order_list.jsp'>List</a>  all Product_order_lists. </li><br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do" >
        <b>輸入商品訂單編號 (如20160630-00000001):</b>
        <input type="text" name="prod_ord_no">
        <input type="submit" value="送出"><font color=blue>(資料格式驗證  by Controller ).</font> 
        <input type="hidden" name="action" value="getPart_For_Display_By_One_PK">
    </FORM>
  </li>

  <jsp:useBean id="product_order_listSvc" scope="page" class="com.product_order_list.model.Product_order_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do" >
       <b>選擇商品訂單編號:</b>
       <select size="1" name="prod_ord_no">
         <c:forEach var="product_order_listVO" items="${product_order_listSvc.all}" > 
          <option value="${product_order_listVO.prod_ord_no}">${product_order_listVO.prod_ord_no}
         </c:forEach>
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getPart_For_Display_By_One_PK">
    </FORM>
  </li>
</ul>

<h3>商品訂單明細管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product_order_list/addProduct_order_list.jsp'>Add</a> a new Product_order_list.</li>
</ul>

</body>

</html>