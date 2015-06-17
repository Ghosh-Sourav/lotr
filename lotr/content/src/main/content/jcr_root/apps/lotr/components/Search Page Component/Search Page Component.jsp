<%--

  searchPageComponent component.

  

--%><%
%><%@include file="/libs/foundation/global.jsp"%>
<%@page session="false" %><%
%><%
	// TODO add you code here
%>
<head>
	<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
</head>

<cq:include path="loadingComponent" resourceType="lotr/components/loadingComponent"/>
<div class="container" style="display:none;" > <!-- ng-app="" ng-controller="pageController" -->

    <cq:include path="header" resourceType="lotr/components/header"/>
    <div class="jumbotron">
    	<cq:include path="parsys" resourceType="wcm/foundation/components/parsys"/>
    </div>
    <cq:include path="footer" resourceType="lotr/components/footer"/>	

    </div>
    