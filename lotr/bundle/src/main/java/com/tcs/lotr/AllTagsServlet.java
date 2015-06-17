package com.tcs.lotr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.tcs.services.TagService;


@SlingServlet(
		paths = "/bin/AllTags",
		methods = { "POST", "GET" },
		metatype = true,
		label = "AllTagsServlet",
		name = "com.tcs.lotr.AllTagsServlet"
		)

public class AllTagsServlet extends SlingAllMethodsServlet {
	
	@Reference
	TagService tagService;
	
	
	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		PrintWriter out=response.getWriter();
		Session session = request.getResource().getResourceResolver().adaptTo(Session.class);
		String tagGroup="";
		
		if(request.getParameter("tagGroup")!=null)
			tagGroup=request.getParameter("tagGroup");
		
		out.println(tagService.getAllTags(tagGroup, session));
	
		out.close();
	}
	
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
	
	

}
