<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>分享食光: 實況區</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>分享食光</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>Welcome~ Guest! You are now at the Live page of FoodTime. </p>

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
  <li><a href='listAllLive.jsp'>List</a> all Lives. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="live.do" >
        <b>輸入實況會員編號 (如M00000001):</b>
        <input type="text" name="mem_no" value="M00000001">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="liveSvc" scope="page" class="com.live.model.LiveService" />
   
  <li>
     <FORM METHOD="post" ACTION="live.do" >
       <b>選擇實況會員編號:</b>
       <select size="1" name="mem_no">
         <c:forEach var="liveVO" items="${liveSvc.all}" > 
          <option value="${liveVO.mem_no}">${liveVO.mem_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
 

</ul>


<h3>實況新增</h3>

<ul>
  <li><a href='addLive.jsp'>Add</a> a new Live.</li>
</ul>

</body>

</html>
