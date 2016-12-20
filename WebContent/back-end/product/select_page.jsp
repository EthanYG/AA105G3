<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Product select</title></head>
<body>

<table border='1' cellpadding='3' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h2>分享食光</h2><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>測試商品 (Product) 搜尋</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp'>List</a>  all Product. </li><br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" >
        <b>輸入商品編號 (如1):</b>
        <input type="text" name="prod_no">
        <input type="submit" value="送出"><font color=blue>(資料格式驗證  by Controller ).</font> 
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" >
       <b>選擇商品編號:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" >
       <b>選擇商品名稱:</b>
       <select size="1" name="prod_no">
         <c:forEach var="productVO" items="${productSvc.all}" > 
          <option value="${productVO.prod_no}">${productVO.prod_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>

<h3>商品管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/product/addProduct.jsp'>Add</a> a new Product.</li>
</ul>

</body>
</html>