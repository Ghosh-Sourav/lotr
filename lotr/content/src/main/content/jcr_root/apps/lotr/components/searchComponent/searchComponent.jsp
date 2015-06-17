<%@include file="/libs/foundation/global.jsp"%><%@page session="false"%>

<span class="offset" style="display: none;"><%=properties.get("Offset") %></span>
<span class="limit" style="display: none;"><%=properties.get("Limit") %></span>
<span class="path" style="display: none;"><%=(properties.get("SearchField")==null || properties.get("SearchField").equals(""))? "/content/lord-of-the-rings/en/middle-earth/" :properties.get("SearchField") %></span>

<div class="jumbotron">
	<!-- ng-app="" -->
	<span class="bg-primary searchFormReloadMsg" style="text-align:center;">
		Please reload the page for changes to reflect	
	</span>
	<form class="form-group form-inline text-center" id="searchForm"
		action="#txtSearch" autocomplete="on" style="display:none;">
		<center>
			<span class="hm bg-primary" style="font-size: 15px; font-family: Segoe UI;"></span>
		</center>
		<br /> &nbsp; <br /> &nbsp;<br />
		<center>
			<span style="height: 40px;"> <input type="checkbox" id="check"
				class="check" style="width: 40px" /></span> <input type="search"
				id="txtSearch" class="form-control" style="width: 70%"
				placeholder="Enter the name of a character, species, or a place in Middle Earth..."
				value="<%=(request.getParameter("q") != null) ? request
					.getParameter("q") : ""%>">
			<!-- ng-model="word" ng-controller="pageController" -->

			&nbsp;
			<button type="submit" id="btnSearch" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Search
			</button>

			<!-- <div>
				<p ng-repeat="x in characters | filter:word"
					value="{{x.title}}">{{5+5}}</p>
			</div> -->
		</center>
	</form>
</div>





