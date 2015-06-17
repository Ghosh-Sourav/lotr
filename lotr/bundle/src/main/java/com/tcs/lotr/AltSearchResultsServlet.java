package com.tcs.lotr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.queryBuilderDemo.AltSearchService;
import com.tcs.queryBuilderDemo.SearchService;

@SlingServlet(paths = "/bin/AltSearchResults", methods = "POST", metatype = true)

/*
 * The url from the jquery is compared with the paths parameter of the sling
 * servlet annotation.Since it matches,the search parameter(g) obatined is sent
 * to post method
 */
public class AltSearchResultsServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Reference
	AltSearchService searchService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AltSearchResultsServlet.class);

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		PrintWriter out = response.getWriter();
		try {
		
			
			Session session = request.getResource().getResourceResolver().adaptTo(Session.class);

			JSONArray jsonArray = searchService.getResults("-1", "0",
					"/content/lord-of-the-rings/en/middle-earth/" , session);
		
			out.println(jsonArray);
			

		} catch (Exception e) {
			LOGGER.error("Exception in Search Servlet");
		}
		finally{
			if(out!=null)
				out.close();
		}
	}

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
}
