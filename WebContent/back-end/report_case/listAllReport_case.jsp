<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report_case.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Report_caseService report_caseSvc = new Report_caseService();
    List<Report_caseVO> list = report_caseSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有檢舉案件資料 - listAllReport_case.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有檢舉案件資料 - ListAllReport_case.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>檢舉項目編號</th>
		<th>檢舉會員編號</th>
		<th>被檢舉案件編號</th>
		<th>檢舉類別</th>
		<th>檢舉原因</th>
		<th>檢舉建立日期</th>
		<th>檢舉審核狀態</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="report_caseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${report_caseVO.rep_no}</td>
			<td>${report_caseVO.rep_mem_no}</td>
			<td>${report_caseVO.rep_tar_no}</td>
			<td>${report_caseVO.rep_type}</td>
			<td>${report_caseVO.rep_reason}</td>
			<td>${report_caseVO.rep_date}</td>
			<td>${report_caseVO.rep_chk_con}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report_case/report_case.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="rep_no" value="${report_caseVO.rep_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report_case/report_case.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="rep_no" value="${report_caseVO.rep_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
