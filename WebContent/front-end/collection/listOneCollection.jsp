<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.collection.model.*"%>
<%
CollectionVO collectionVO = (CollectionVO) request.getAttribute("collectionVO"); //CollectionServlet.java(Concroller), 存入req的collectionVO物件
%>
<html>
<head>
<title>我的最愛資料 - listOneCollection.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>我的最愛資料 - ListOneCollection.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/collection/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>我的最愛編號</th>
		<th>會員編號</th>
		<th>收藏目標編號</th>
		<th>類別編號</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=collectionVO.getColl_no()%></td>
		<td><%=collectionVO.getMem_no()%></td>
		<td><%=collectionVO.getAll_no()%></td>
		<td><%=collectionVO.getClass_no()%></td>
	</tr>
</table>

</body>
</html>
