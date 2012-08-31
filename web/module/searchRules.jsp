<%@ include file="/WEB-INF/template/include.jsp" %>
	
<%@ include file="/WEB-INF/template/header.jsp" %>
<link href="${pageContext.request.contextPath}/moduleResources/dss/dss.css" type="text/css" rel="stylesheet" />
<p><b>Please enter rule search terms:</b></p>
<form name="input" action="searchRules.form" method="get">
<table>
<tr><td>Restrict on <b>title:</b></td><td> <input type="text" name="title" value="${title}" size="20"></td>
<td>Restrict on <b>author:</b></td><td> <input type="text" name="author" value="${author}" size="20"></td></tr>
<tr><td>Restrict on <b>keywords:</b></td><td> <input type="text" name="keywords" value="${keywords}" size="20"></td>
<td>Restrict on <b>rule type:</b></td><td> <input type="text" name="ruleType" value="${ruleType}" size="20"></td></tr>
<tr><td>Restrict on <b>action:</b></td><td> <input type="text" name="action" value="${action}" size="20"></td>
<td>Restrict on <b>logic:</b></td><td> <input type="text" name="logic" value="${logic}" size="20"></td></tr>
<tr><td>Restrict on <b>data:</b></td><td><input type="text" name="data" value="${data}" size="20"></td>
<td>Restrict on <b>links:</b></td><td> <input type="text" name="links" value="${links}" size="20"></td></tr>
<tr><td>Restrict on <b>citations:</b></td><td> <input type="text" name="citations" value="${citations}" size="20"></td>
<td>Restrict on <b>explanation:</b></td><td> <input type="text" name="explanation" value="${explanation}" size="20"></td></tr>
<tr><td>Restrict on <b>purpose:</b></td><td> <input type="text" name="purpose" value="${purpose}" size="20"></td>
<td>Restrict on <b>specialist:</b></td><td><input type="text" name="specialist" value="${specialist}" size="20"></td></tr>
<tr><td>Restrict on <b>institution:</b></td><td><input type="text" name="institution" value="${institution}" size="20"></td>
<td>Restrict on <b>class file name:</b></td><td><input type="text" name="classFilename" value="${classFilename}" size="20"></td></tr>
<tr><td colspan="4" style="text-align:right"><input type="submit" value="OK"></td></tr>
</table>
<input type="hidden" name="runSearch" value="true"/>
</form>
<c:if test="${runSearch}">
	Here are the matching rules:<br><br>
<c:forEach items="${rules}" var="databaseRule">
		<table style="border-width: 2px;border-style: solid;" width="100%">
		<tr>
		<td><b>Rule&nbsp;Id:</b></td>
		<td>${databaseRule.ruleId}</td>
		</tr>
		<tr>
		<td><b>Title:</b></td>
		<td>${databaseRule.title}</td>
		</tr>
		<tr>
		<td><b>Class&nbsp;File&nbsp;name:</b></td>
		<td>${databaseRule.classFilename}</td>
		</tr>
		<tr>
		<td><b>Creation&nbsp;time:</b></td>
		<td>${databaseRule.creationTime}</td>
		</tr>
		<tr>
		<td><b>Priority:</b></td>
		<td>${databaseRule.priority}</td>
		</tr>
		<tr>
		<td><b>Version:</b></td>
		<td>${databaseRule.version}</td>
		</tr>
		<tr>
		<td><b>Institution:</b></td>
		<td>${databaseRule.institution}</td>
		</tr>
		<tr>
		<td><b>Author:</b></td>
		<td>${databaseRule.author}</td>
		</tr>
		<tr>
		<td><b>Specialist:</b></td>
		<td>${databaseRule.specialist}</td>
		</tr>
		<tr>
		<td><b>Rule&nbsp;Creation&nbsp;Date:</b></td>
		<td>${databaseRule.ruleCreationDate}</td>
		</tr>
		<tr>
		<td><b>Purpose:</b></td>
		<td>${databaseRule.purpose}</td>
		</tr>
		<tr>
		<td><b>Explanation:</b></td>
		<td>${databaseRule.explanation}</td>
		</tr>
		<tr>
		<td><b>Keywords:</b></td>
		<td>${databaseRule.keywords}</td>
		</tr>
		<tr>
		<td><b>Citations:</b></td>
		<td>${databaseRule.citations}</td>
		</tr>
		<tr>
		<td><b>Links:</b></td>
		<td>${databaseRule.links}</td>
		</tr>
		<tr>
		<td><b>Data:</b></td>
		<td>${databaseRule.data}</td>
		</tr>
		<tr>
		<td><b>Logic:</b></td>
		<td>${databaseRule.logic}</td>
		</tr>
		<tr>
		<td><b>Action:</b></td>
		<td>${databaseRule.action}</td>
		</tr>
		<tr>
		<td><b>Rule&nbsp;Type:</b></td>
		<td>${databaseRule.ruleType}</td>
		</tr>
		<tr>
		<td><b>Last&nbsp;Modified:</b></td>
		<td>${databaseRule.lastModified}</td>
		</tr>
		</table>
	</c:forEach>
	</c:if>
<%@ include file="/WEB-INF/template/footer.jsp" %>