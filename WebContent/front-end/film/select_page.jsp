<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>���ɭ���: �v����</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>���ɭ���</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>Welcome~ Guest! You are now at the Film page of FoodTime. </p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
        <b>��J�v���s�� (�pF00000001):</b>
        <input type="text" name="film_no" value="F00000001">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="filmSvc" scope="page" class="com.film.model.FilmService" />
   
  <li>
     <FORM METHOD="post" ACTION="film.do" >
       <b>��ܼv���s��:</b>
       <select size="1" name="film_no">
         <c:forEach var="filmVO" items="${filmSvc.all}" > 
          <option value="${filmVO.film_no}">${filmVO.film_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
 

</ul>


<h3>�v���s�W</h3>

<ul>
  <li><a href='addFilm.jsp'>Add</a> a new Film.</li>
</ul>

</body>

</html>
