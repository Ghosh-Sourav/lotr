package com.tcs.queryBuilderDemo;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;

public interface SearchService {
	
	public JSONArray getResults(String inputQuery, String limit, String offset, String searchPath ,Session session);

}
