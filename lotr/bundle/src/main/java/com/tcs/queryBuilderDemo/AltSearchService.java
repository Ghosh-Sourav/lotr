package com.tcs.queryBuilderDemo;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;

public interface AltSearchService {
	
	public JSONArray getResults(String limit, String offset, String searchPath ,Session session);

}
