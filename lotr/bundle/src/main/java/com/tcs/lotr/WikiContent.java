package com.tcs.lotr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.tcs.services.WikiService;



@SlingServlet(paths = "/bin/WikiContent", methods = "POST", metatype = true)

public class WikiContent extends SlingAllMethodsServlet {
	
	@Reference
	WikiService wikiService;
	
	
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		PrintWriter out=response.getWriter();
		
		
		String query="";
		
		if(request.getParameter("query")!=null)
			query=request.getParameter("query");
		try{
			out.println(wikiService.getContent(query));
		}
		catch(Exception e){
			out.println("Content could not be fetched from Wikipedia because of network issue.");
		}
		
		
		out.close();
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

}
