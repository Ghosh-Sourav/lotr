<%--

  Legend Component component.

  

--%>
<%@include file="/libs/foundation/global.jsp"%>
<head>
	<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
</head>



<%@page session="false" %><%
%><%
	// TODO add you code here
%>

<cq:include path="loadingComponent" resourceType="lotr/components/loadingComponent"/>

<div class="container" style="display:none;" >
    <cq:include path="header" resourceType="lotr/components/header" />
	<div class="jumbotron img-circle">
		<div>
			<h2><%=currentPage.getTitle()%></h2>			
		</div>
		<hr/>
		<div>
			<cq:include path="legendContent" resourceType="wcm/foundation/components/textimage"/>
			
			<%-- <%
							if(currentPage.getProperties().get("newLegendData")==null) {
								Node node = resource.adaptTo(Node.class);
								String wikiContent="";
								
								
								WikiService wikiService=null;
								wikiService=new WikiServiceImpl();
								
								try{
									wikiContent=wikiService.getContent(currentPage.getTitle());
								}
								catch(Exception e){
									wikiContent=e.getMessage().toString();
								}
								
								
								node.setProperty("newLegendData", wikiContent);
								node.getSession().save();
							}
						%>
			<%=properties.get("newLegendData") %>	 --%>		
		</div>
        <hr/>
        <div class="jumbotron">
    	<cq:include path="parsys" resourceType="wcm/foundation/components/parsys"/>
    </div>
		<hr/>
		<div>
			<cq:include path="wiki" resourceType="lotr/components/wikiComponent"/>
		</div>
	</div>
	<cq:include path="footer" resourceType="lotr/components/footer" />
</div>

