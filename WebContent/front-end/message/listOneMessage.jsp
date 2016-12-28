<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //MessageServlet.java(Concroller), 存入req的messageVO物件
%>
<html>
<head>
<title>留言資料 - listOneMessage.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料 - ListOneMessage.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>
  


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>主題留言編號</th>
		<th>會員編號</th>
		<th>被留言會員編號</th>
		<th>相關留言編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
	</tr>
			

			
	<tr align='center' valign='middle'>
			<td>${messageVO.msg_no}</td>
			<td>${messageVO.mem_no}</td>
			<td>${messageVO.msg_mem_no}</td>
			<td>${messageVO.msg_rel}</td>
			<td>${messageVO.msg_detail}</td>
			<td>${messageVO.msg_date}</td>
		

		
	</tr>
</table>

</body>
</html>
