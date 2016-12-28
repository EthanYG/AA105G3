<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>分享食光: 影片區</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>分享食光</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>Welcome~ Guest! You are now at the Message page of FoodTime. </p>

<h3>影片資料查詢:</h3>
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
  <li><a href='listAllMessage.jsp'>List</a> all Messages. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="message.do" >
        <b>輸入留言編號 (如ME00000001):</b>
        <input type="text" name="msg_no" placeholder="ME00000001">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="MessageSvc" scope="page" class="com.message.model.MessageService" />
   
  <li>
     <FORM METHOD="post" ACTION="message.do" >
       <b>選擇留言編號:</b>
       <select size="1" name="msg_no">
         <c:forEach var="MessageVO" items="${MessageSvc.all}" > 
          <option value="${MessageVO.msg_no}">${MessageVO.msg_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
 

</ul>


<h3>留言新增</h3>

<ul>
  <li><a href='addMessage.jsp'>Add</a> a new Message.</li>
</ul>

</body>

</html>
