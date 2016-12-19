<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM FoodTime: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM FoodTime: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM FoodTime: Home</p>

<h3>資料查詢:</h3>
<h5>全部查詢:</h5>
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
  <li><a href='listAllFrd_list.jsp'>List</a> all Frd_lists. </li> <br><br>
  
  <li>
  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frd_list/frd_list.do" >
        <b>輸入會員編號 (如M00000001):</b>
        <input type="text" name="mem_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getAllByMem_no_For_Display">
        </FORM> 
  </li> <br><br>

</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addFrd_list.jsp'>Add</a> a new Frd_list.</li>
</ul>

</body>

</html>
