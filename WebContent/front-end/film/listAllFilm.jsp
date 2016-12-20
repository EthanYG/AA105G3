<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.film.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    FilmService filmSvc = new FilmService();
    List<FilmVO> list = filmSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ����u��� - listAllFilm.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllFilm.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�v���s��</th>
		<th>�|���s��</th>
		<th>�v�����D</th>
		<th>�v�����e</th>
		<th>�v���W�Ǯɶ�</th>
		<th>�x�s�v���ɮ�</th>
		<th>�v���g��</th>
		<th>�v���H��</th>
		<th>�ק�</th>
		<th>�R��</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="filmVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${filmVO.film_no}</td>
			<td>${filmVO.mem_no}</td>
			<td>${filmVO.film_title}</td>
			<td>${filmVO.film_content}</td>
			<td>${filmVO.film_date}</td>
			<td>${filmVO.film_file}</td>
			<td>${filmVO.film_like}</td>
			<td>${filmVO.film_popular}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/film/film.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="film_no" value="${filmVO.film_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/film/film.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="film_no" value="${filmVO.film_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
