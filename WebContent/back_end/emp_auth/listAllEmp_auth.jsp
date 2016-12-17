<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp_auth.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.auth.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
    Set<Emp_authVO> set = new LinkedHashSet<Emp_authVO>();  
%>
<jsp:useBean id="authSvc" scope="page" class="com.auth.model.AuthService" />
<jsp:useBean id="emp_authSvc" scope="page" class="com.emp_auth.model.Emp_authService" />

<html>
<head>
<title>所有員工權限資料 - listAllEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工權限資料 - ListAllEmp_auth.jsp</h3>
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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>員工編號</th>
		<th>擁有權限名稱</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="EmpVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${EmpVO.emp_no}</td>
			<td>
				<c:forEach var="Emp_authVO" items="${emp_authSvc.all}">
                    <c:if test="${EmpVO.emp_no==Emp_authVO.emp_no}">
	                    ${Emp_authVO.auth_no}【<font color=orange>${authSvc.getOneAuth(Emp_authVO.auth_no).auth_name}</font>】<br>
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp_auth/emp_auth.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_no" value="${EmpVO.emp_no}">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="requestURL" vlaue="<%=request.getServletPath()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
