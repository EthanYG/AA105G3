<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>分享食光: 檢舉案件區</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>分享食光</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>Welcome~ Guest! You are now at the Report_case page of FoodTime. </p>

<h3>資料查詢:</h3>
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

<ul>
  <li><a href='listAllReport_case.jsp'>List</a> all Report_cases. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="report_case.do" >
        <b>輸入檢舉項目編號 (如RE00000001):</b>
        <input type="text" name="rep_no" value="RE00000001">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="report_caseSvc" scope="page" class="com.report_case.model.Report_caseService" />
   
  <li>
     <FORM METHOD="post" ACTION="report_case.do" >
       <b>選擇檢舉項目編號:</b>
       <select size="1" name="rep_no">
         <c:forEach var="report_caseVO" items="${report_caseSvc.all}" > 
          <option value="${report_caseVO.rep_no}">${report_caseVO.rep_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
 

</ul>


<h3>新增檢舉資料</h3>

<ul>
  <li><a href='addReport_case.jsp'>Add</a> a new Report_case.</li>
</ul>

</body>

</html>
