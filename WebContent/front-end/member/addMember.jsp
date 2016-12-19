<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<title>會員資料新增 - addMember.jsp</title></head>
<script language="JavaScript" src="js/member_image.js"></script>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料新增 - addMember.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp">
		   <img src="<%=request.getContextPath()%>/front-end/member/images/back.png" width="70" height="70" border="0">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料會員:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>會員暱稱:</td>
		<td><input type="TEXT" name="mem_name" size="45" 
		value="${memberVO==null ? '新的會員' : memberVO.mem_name}" /></td>
			<%-- value="<%= (memberVO==null)? "新的會員" : memberVO.getMem_name()%>" /></td> --%>			
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="mem_ac" size="45"
			value="${memberVO==null ? 'FoodTime' : memberVO.mem_ac}" /></td>
			<%-- value="<%= (memberVO==null)? "FoodTime" : memberVO.getMem_ac()%>" /></td> --%>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="PASSWORD" name="mem_pw" size="45"
			value="${memberVO==null ? 'foodtime' : memberVO.mem_pw}" /></td>
			<%-- value="<%= (memberVO==null)? "foodtime" : memberVO.getMem_pw()%>" /></td> --%>
	</tr>
	<tr>
		<td>會員頭像:</td>
		<td><input accept="image/*" type="FILE" name="mem_image" id="mem_image" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="RADIO" name="mem_sex" value="1" />男
			<input type="RADIO" name="mem_sex" value="0" />女</td>
	</tr>
	<tr>
		<td>手機:</td>
		<td><input type="TEXT" name="mem_phone" size="45"
			value="${memberVO==null ? '0963258741' : memberVO.mem_phone}" /></td>
			<%-- value="<%= (memberVO==null)? "0963258741" : memberVO.getMem_phone()%>" /></td> --%>
	</tr>
	<tr>
		<td>電子郵件:</td>
		<td><input type="TEXT" name="mem_email" size="45"
			value="${memberVO==null ? 'foodtime123@gmail.com' : memberVO.mem_email}" /></td>
			<%-- value="<%= (memberVO==null)? "foodtime123@gmail.com" : memberVO.getMem_email()%>" /></td> --%>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="mem_adrs" size="45"
			value="${memberVO==null ? '太陽系第三行星-地球' : memberVO.mem_adrs}" /></td>
			<%-- value="<%= (memberVO==null)? "太陽系第三行星-地球" : memberVO.getMem_adrs()%>" /></td> --%>
	</tr>
	<tr>
		<td>會員資格:</td>
		<td><input type="RADIO" name="mem_own" value="0" checked />一般會員
			<input type="RADIO" name="mem_own" value="1" />私廚</td>
	</tr>
	<tr>
		<td>影片觀看紀錄:</td>
		<td><input type="TEXT" name="mem_history" size="45"
			value="${memberVO==null ? 'F00000001' : memberVO.mem_history}" /></td>
			<%-- value="<%= (memberVO==null)? "F00000001" : memberVO.getMem_history()%>" /></td> --%>
	</tr>
	<tr>
		<td>是否在線:</td>
		<td><input type="RADIO" name="mem_online" value="1" checked />在線
			<input type="RADIO" name="mem_online" value="0" />不在線</td>
	</tr>
	
	<tr>
		<img id="img" src="<%=request.getContextPath()%>/front-end/member/images/No-image-found.png" width='100'>
	</tr>

</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>