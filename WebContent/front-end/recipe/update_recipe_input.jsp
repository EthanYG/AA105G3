<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.auth.model.*"%>
<%
	/* RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO"); */  
%>
<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="ingredients" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="quantity" scope="request" class="java.util.ArrayList"/>
<html>
<head>
<title>食譜資料修改 - update_recipe_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/showImg.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜資料修改 - update_recipe_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/recipe/select_page.jsp">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe/recipe.do" name="form1"  enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>食譜編號:<font color=red>${recipeVO.recipe_no}</font></td>
	</tr>
	<tr>
		<td>食譜名稱:<input type="TEXT" name="recipe_name" size="45" value=${recipeVO.recipe_name} /></td>
	</tr>
	<tr>
		<td>食譜簡介:</td>
	</tr>
	<tr>
		<td><textarea name="recipe_intro" rows="6" cols="80">${recipeVO.recipe_intro}</textarea></td>
	</tr>
	<tr>
		<td>食材:</td>
	</tr>
	
	<c:forEach var="ingredient" items="${ingredients}" varStatus="s">
		<tr>
			<td><input type="TEXT" name="ingredients" size="10" value="${ingredients[s.index]}" placeholder="食材">
			<input type="TEXT" name="quantity" size="10" value="${quantity[s.index]}" placeholder="份量"></td>
		</tr>
	</c:forEach>
	<tr>
		<td>食譜圖片:</td>
		
	</tr>
	<tr>
		<td>
		<input type="file" name="recipe_pic" id = "upLoadFile" onchange="showImage()"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="recipe_no" value=${recipeVO.recipe_no}>
<input type="submit" value="送出修改"></FORM>
<img id="image" src="<%=request.getContextPath()%>/recipe/ShowRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" style="width:300px;"/>

</body>
</html>
