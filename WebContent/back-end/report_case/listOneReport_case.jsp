<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.report_case.model.*"%>
<%
Report_caseVO report_caseVO = (Report_caseVO) request.getAttribute("report_caseVO"); //Report_caseServlet.java(Concroller), 存入req的report_caseVO物件
%>
<html>
<head>
<title>員工資料 - listOneReport_case.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneReport_case.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>檢舉項目編號</th>
		<th>檢舉會員編號</th>
		<th>被檢舉案件編號</th>
		<th>檢舉類別</th>
		<th>檢舉原因</th>
		<th>檢舉建立日期</th>
		<th>檢舉審核狀態</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=report_caseVO.getRep_no()%></td>
		<td><%=report_caseVO.getRep_mem_no()%></td>
		<td><%=report_caseVO.getRep_tar_no()%></td>
		<td><%=report_caseVO.getRep_type()%></td>
		<td><%=report_caseVO.getRep_reason()%></td>
		<td><%=report_caseVO.getRep_date()%></td>
		<td><%=report_caseVO.getRep_chk_con()%></td>
		
	</tr>
</table>

</body>
</html>
