<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.message.model.*"%>
<%
MessageVO messageVO = (MessageVO) request.getAttribute("messageVO"); //MessageServlet.java(Concroller), �s�Jreq��messageVO����
%>
<html>
<head>
<title>�d����� - listOneMessage.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d����� - ListOneMessage.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>
  


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>�D�D�d���s��</th>
		<th>�|���s��</th>
		<th>�Q�d���|���s��</th>
		<th>�����d���s��</th>
		<th>�d�����e</th>
		<th>�d���ɶ�</th>
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
