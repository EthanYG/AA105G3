<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.film.model.*"%>
<%
FilmVO filmVO = (FilmVO) request.getAttribute("filmVO"); //FilmServlet.java(Concroller), 存入req的filmVO物件
%>
<html>
<head>
<title>員工資料 - listOneFilm.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneFilm.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>影片編號</th>
		<th>會員編號</th>
		<th>影片標題</th>
		<th>影片內容</th>
		<th>影片上傳時間</th>
		<th>儲存影片檔案</th>
		<th>影片讚數</th>
		<th>影片人氣</th>
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
