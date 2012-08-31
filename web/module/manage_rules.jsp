<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<link href="${pageContext.request.contextPath}/moduleResources/dss/dss.css" type="text/css" rel="stylesheet" />
<p><b>Please choose a rule action:</b></p>
<form action="deleteRules.form" method="get">
<input type="submit" value="Delete rules" />
</form><br>
<form action="runRules.form" method="get">
<input type="submit" value="Run rules" />
</form><br>
<form action="searchRules.form" method="get">
<input type="submit" value="Search rules" />
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>