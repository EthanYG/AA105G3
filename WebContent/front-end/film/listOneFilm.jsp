<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.film.model.*"%>
<%
FilmVO filmVO = (FilmVO) request.getAttribute("filmVO"); //FilmServlet.java(Concroller), �s�Jreq��filmVO����
%>
<html>
<head>
<title>���u��� - listOneFilm.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��� - ListOneFilm.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>�v���s��</th>
		<th>�|���s��</th>
		<th>�v�����D</th>
		<th>�v�����e</th>
		<th>�v���W�Ǯɶ�</th>
		<th>�x�s�v���ɮ�</th>
		<th>�v���g��</th>
		<th>�v���H��</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=filmVO.getFilm_no()%></td>
		<td><%=filmVO.getMem_no()%></td>
		<td><%=filmVO.getFilm_title()%></td>
		<td><%=filmVO.getFilm_content()%></td>
		<td><%=filmVO.getFilm_date()%></td>
		<td><%=filmVO.getFilm_file()%></td>
		<td><%=filmVO.getFilm_like()%></td>
		<td><%=filmVO.getFilm_popular()%></td>
	</tr>
</table>

</body>
</html>
