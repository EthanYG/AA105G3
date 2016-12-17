<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp_auth.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.auth.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    /* EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
    Set<Emp_authVO> set = new LinkedHashSet<Emp_authVO>();   */
%>
<jsp:useBean id="authSvc" scope="page" class="com.auth.model.AuthService" />
<jsp:useBean id="emp_authSvc" scope="page" class="com.emp_auth.model.Emp_authService" />
<jsp:useBean id="listAuths_ByEmp_no" scope="request" type="java.util.List" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工權限修改 - update_emp_auth.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工權限修改 - update_emp_auth.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/emp_auth/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>


<FORM METHOD="post" ACTION="emp_auth.do" name="form1">
<h3>${param.emp_no}:</h3>
<table border='1' bordercolor='#CCCCFF'>
	<tr>
		<c:forEach var = "authVO" items="${authSvc.all}">
		<th>
			${authVO.auth_name}
		</th>
		</c:forEach>
	</tr>
	
	<tr>
		<c:forEach var = "authVO" items="${authSvc.all}">
		<td>
			<input type="checkbox" name="emp_auths" value = "${authVO.auth_no}" ${listAuths_ByEmp_no.contains(authVO.auth_no)? 'checked' :''}>
		</td>
		</c:forEach>
	</tr>
</table>
<input type="hidden" name="action" value="updateAuths_ByEmp_no">
<input type="hidden" name="emp_no" value="${param.emp_no}">

<input type="submit" value="送出修改"></FORM>
</FORM>

</body>
</html>