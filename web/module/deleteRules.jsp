<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<link href="${pageContext.request.contextPath}/moduleResources/dss/dss.css" type="text/css" rel="stylesheet" />
<c:forEach items="${rulesToDelete}" var="currRuleToDelete">
Deleting rule: ${currRuleToDelete}<br><br>
</c:forEach>

<p><b>Delete the following rules:</b></p>
<form name="input" action="deleteRules.form" method="get">
<table>
<tr style="padding: 5px">
<td>Please highlight the rules to delete:</td>
<td><input type="submit" value="OK"></td>
</tr>
<tr style="padding: 5px">
<td colspan="2" style="text-align:right">
<select name="RulesToDelete" multiple>
<c:forEach items="${rules}" var="rule">
<option value="${rule.ruleId}">${rule.tokenName}</option>
</c:forEach>
</select>
</td>
</tr>
</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>