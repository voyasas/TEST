<%@ page errorPage="error.jsp"%>

<%@ taglib uri="ifs.tld" prefix="ifs" %>
<ifs:security configfile="tpascheda.xml" />

<ifs:GetBeanClassName/>
<ifs:ifNotNull variable="<%=FormBean%>">
	<ifs:setProperty/>
</ifs:ifNotNull>

<ifs:Validate/>

<ifs:ifTrue flag="<%=validateRC%>">
	<ifs:businessInteraction />
</ifs:ifTrue>

<ifs:initPage/>

<jsp:forward page="<%=pageForward%>"/>


