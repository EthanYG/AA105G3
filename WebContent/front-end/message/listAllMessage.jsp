<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.message.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MessageService messageSvc = new MessageService();
    List<MessageVO> list = messageSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有留言資料 - listAllMessage.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有留言資料 - ListAllMessage.jsp</h3>
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

<!-- 	private static final String GET_ALL_STMT = "SELECT msg_no,mem_no,msg_mem_no,msg_rel,msg_detail,msg_date FROM message order by msg_no"; -->

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>主題留言編號</th>
		<th>會員編號</th>
		<th>被留言會員編號</th>
		<th>相關留言編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${messageVO.msg_no}</td>
			<td>${messageVO.mem_no}</td>
			<td>${messageVO.msg_mem_no}</td>
			<td>${messageVO.msg_rel}</td>
			<td>${messageVO.msg_detail}</td>
			<td>${messageVO.msg_date}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/message/message.do">
			     <input type="submit" value="留言內容修改">
			     <input type="hidden" name="msg_no" value="${messageVO.msg_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/message/message.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="msg_no" value="${messageVO.msg_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
