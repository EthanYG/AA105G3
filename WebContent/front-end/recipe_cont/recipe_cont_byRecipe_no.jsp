<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="recipe_cont_set" scope="request" type="java.util.Set" />
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="recipe_contSvc" scope="page" class="com.recipe_cont.model.Recipe_contService" />

<html>
<head>
<title>食譜步驟內容資料 - recipe_cont_byRecipe_no.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜步驟內容 - recipe_cont_byRecipe_no.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/recipe_cont/select_page.jsp">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='700'>
	<tr>
		<td>食譜編號:<font color=red>${param.recipe_no}</font></td>
	</tr>
	<tr>
		<td>食譜名稱:>${recipeSvc.getOneRecipe(param.recipe_no).recipe_name}</td>
	</tr>
	<c:forEach var="Recipe_contVO" items="${recipe_cont_set}">
		<tr>
			<c:if test="${Recipe_contVO.step_pic != null}">
			<td width="200">
				<img src="<%=request.getContextPath()%>/recipe_cont/showRecipe_cont_pic.do?recipe_no=${param.recipe_no}&step=${Recipe_contVO.step}"   style="width:200px;"/>
			</td>
			</c:if>
			
			<td>
                ${Recipe_contVO.step}<br>
                ${Recipe_contVO.step_cont}<br>
                
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>