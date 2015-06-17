package com.tcs.services.impl;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*import org.apache.commons.compress.utils.IOUtils;*/
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.queryBuilderDemo.SearchServiceImpl;
import com.tcs.services.WikiService;

@Component
@Service(value=WikiService.class)
public class WikiServiceImpl implements WikiService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WikiServiceImpl.class);

	
	public String getContent(String query) {

		if(query.equals(""))
			query="Lord%20of%20the%20Rings";
		
		String jsonString="",outputString="";
		
		try {
			// Set the http proxy to proxy.tcs.com:8080
			System.setProperty("http.proxyHost", "proxy.tcs.com");
			System.setProperty("http.proxyPort", "8080");

			jsonString+= new Scanner(new URL("http://en.wikipedia.org/w/api.php?format=json&action=query&titles="+query+"&prop=revisions&rvprop=content").openStream(), "UTF-8").useDelimiter("\\A").next();
			
			// Now, let's 'unset' the proxy.
			System.setProperty("http.proxyHost", null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonString+=e.getStackTrace().toString();
			LOGGER.error(e.getStackTrace().toString());
		}
		
		LOGGER.info("Wikipedia JSON String obtained");
		
		try {
			JSONObject jsonObject=new JSONObject(jsonString);
			jsonObject=jsonObject.getJSONObject("query").getJSONObject("pages");
			
			Iterator<String> iterator=jsonObject.keys();
			
			while (iterator.hasNext()){
				String key = iterator.next();
				outputString = jsonObject.getJSONObject(key).getJSONArray("revisions").getJSONObject(0).get("*").toString();
				
				
			}
			
			//outputString=jsonObject.toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			LOGGER.error("JSON Exception occcured"+e.getStackTrace());
			return "No further content available.";
		}
		
		
		return outputString.replaceAll("\\<[^>]*>","").replace("[[", "").replace("]]", "").replace(" ==", "<br/><br/> ==");
	}
	
	

}
