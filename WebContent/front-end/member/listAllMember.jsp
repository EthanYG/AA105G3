<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料 - listAllMember.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/member/images/back.png" width="70" height="70" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>會員編號</th>
		<th>會員暱稱</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<!-- <th>會員頭像</th> -->
		<th>會員性別</th>
		<th>會員手機</th>
		<th>會員電子郵件</th>
		<th>會員地址</th>
		<th>會員資格</th>
		<th>會員影片觀看紀錄</th>
		<th>會員是否在線</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<c:forEach var="memberVO" items="${list}">
		<tr align='center' valign='middle'}>
			<td>${memberVO.mem_no}</td>
			<td>${memberVO.mem_name}</td>
			<td>${memberVO.mem_ac}</td>
			<%-- <td>${memberVO.mem_pw}</td> --%>
			<td><img src="/AA105G3/MemberDBGifReader.do?name=${memberVO.mem_no}" width='100'></td>
			<%-- <td><img src="ShowMember_image.do?mem_no=${memberVO.mem_no}" width='100'></td> --%>
			<td>${memberVO.mem_sex==0 ? '女' : '男'}</td>
			<td>${memberVO.mem_phone}</td>
			<td>${memberVO.mem_email}</td>
			<td>${memberVO.mem_adrs}</td>
			<td>${memberVO.mem_own==0 ? '一般會員' : '私廚'}</td>
			<td>${memberVO.mem_history}</td>
			<td>${memberVO.mem_online==0 ? '不在線' : '在線'}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>