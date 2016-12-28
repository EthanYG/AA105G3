<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.live.model.*"%>
<%
LiveVO liveVO = (LiveVO) request.getAttribute("liveVO"); //LiveServlet.java(Concroller), 存入req的liveVO物件
%>
<html>
<head>
<title>員工資料 - listOneLive.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneLive.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>會員編號</th>
		<th>實況名稱</th>
		<th>實況簡介</th>
		<th>實況總觀看次數</th>
		<th>實況追隨人數</th>
		<th>實況狀態</th>
	</tr>
	

	<tr align='center' valign='middle'>
		<td><%=liveVO.getMem_no()%></td>
		<td><%=liveVO.getLive_name()%></td>
		<td><%=liveVO.getLive_intro()%></td>
		<td><%=liveVO.getLive_counts()%></td>
		<td><%=liveVO.getLive_follow()%></td>
		<td><%=liveVO.getLive_status()%></td>
	</tr>
</table>

</body>
</html>
