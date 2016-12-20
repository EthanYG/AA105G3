<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>分享食光: 影片區</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>分享食光</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>Welcome~ Guest! You are now at the Film page of FoodTime. </p>

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
  <li><a href='listAllFilm.jsp'>List</a> all Films. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="film.do" >
        <b>輸入影片編號 (如F00000001):</b>
        <input type="text" name="film_no" value="F00000001">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="filmSvc" scope="page" class="com.film.model.FilmService" />
   
  <li>
     <FORM METHOD="post" ACTION="film.do" >
       <b>選擇影片編號:</b>
       <select size="1" name="film_no">
         <c:forEach var="filmVO" items="${filmSvc.all}" > 
          <option value="${filmVO.film_no}">${filmVO.film_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
 

</ul>


<h3>影片新增</h3>

<ul>
  <li><a href='addFilm.jsp'>Add</a> a new Film.</li>
</ul>

</body>

</html>
