<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.recipe.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="ingredients" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="quantity" scope="request" class="java.util.ArrayList"/>
<html>
<head>
<title>食譜資料 - listOneRecipe.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜資料 - ListOneRecipe.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/recipe/select_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1800'>
	<tr>
		<th>食譜編號</th>
		<th>食譜圖片</th>
		<th>作者</th>
		<th>食譜名稱</th>
		<th width="450">食譜簡介</th>
		<th width="150">食材</th>
		<th>食譜按讚人數</th>
		<th>食譜總人氣</th>
		<th>食譜周人氣</th>
		<th>食譜上傳時間</th>
		
		
	</tr>
	<tr align='center' valign='middle'>
		<td>${recipeVO.recipe_no}</td>
		<td>
			<img src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" style="width:100px;"/>
		</td>
		<td>${recipeVO.mem_no}</td>
		<td>${recipeVO.recipe_name}</td>
		<td>${recipeVO.recipe_intro}</td>
		<td>
			<c:forEach var="ingredient" items="${ingredients}" varStatus="s">
				${ingredient}&nbsp; &nbsp;
				${quantity[s.index]}<br>
			</c:forEach>
		</td>
		<td>${recipeVO.recipe_like}</td>
		<td>${recipeVO.recipe_total_views}</td>
		<td>${recipeVO.recipe_week_views}</td>
		<td>
			<fmt:formatDate value="${recipeVO.recipe_time}" var="formattedDate" 
                type="date" pattern="yyyy-MM-dd hh:mm:ss aa" />
			${formattedDate}
		</td>
			
	</tr>
</table>

</body>
</html>
