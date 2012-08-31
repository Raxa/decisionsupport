<%@ include file="/WEB-INF/template/include.jsp" %>
	
<%@ include file="/WEB-INF/template/header.jsp" %>
<link href="${pageContext.request.contextPath}/moduleResources/dss/dss.css" type="text/css" rel="stylesheet" />
 <c:choose>
     <c:when test="${patientId == ''}">         
<p><b>Run rules for the following patient:</b></p>
<form name="input" action="runRules.form" method="get">
<table style="padding: 5px">
<tr>
<td>Please enter the patientId:</td>
<td><input type="text" name="patientId" value="${patientId}" size="20"></td>
</tr>
<tr>
<td colspan="2" style="text-align:right"><input type="submit" value="OK"></td>
</tr>
</table>
</form>
   </c:when>
       
        <c:otherwise> 
	Running all rules in dss_rule table...<br/><br />
	<c:forEach items="${rules}" var="rule">
	Results for ${rule.tokenName} rule: ${rule.result} <br/>
	</c:forEach>
        </c:otherwise>
    </c:choose>

<%@ include file="/WEB-INF/template/footer.jsp" %>