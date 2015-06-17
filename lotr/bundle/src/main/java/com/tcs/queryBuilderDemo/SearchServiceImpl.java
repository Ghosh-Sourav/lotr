package com.tcs.queryBuilderDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component
@Service(value = SearchService.class)
public class SearchServiceImpl implements SearchService {

	@Reference
	QueryBuilder queryBuilder;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchServiceImpl.class);

	public JSONArray getResults(String inputQuery, String limit, String offset,
			String searchPath , Session session) {

		JSONArray jsonArray = new JSONArray();
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "cq:Page");
		map.put("p.offset", offset);
		map.put("p.limit", limit);
		map.put("path", searchPath);
		
		if (inputQuery.contains(":")) {
			map.put("tagid", inputQuery);
			map.put("tagid.property", "jcr:content/cq:tags");			
		}
		else {
			map.put("fulltext", inputQuery);
			/*
			map.put("property", "jcr:content/legendContent/text"); 
			map.put("property.operation", "like");
			map.put("property.value", "%"+inputQuery+"%");	// Needs %25 if testing through querybuilder.json URL parameters method
			*/
		}
		
		
		LOGGER.info("Inside the getResults Method");

		Query query = queryBuilder
				.createQuery(PredicateGroup.create(map), session);
		SearchResult result = query.getResult();
		List<Hit> results = result.getHits();
		Iterator<Hit> iterator = results.iterator();
		LOGGER.info("The Result Size is " + results.size());
		
				
		/*
		QueryManager qm;
		String x="";
		try {
			qm = session.getWorkspace().getQueryManager();
			javax.jcr.query.Query eq = qm.createQuery("//*[jcr:contains(., '"+inputQuery+"')]/(@Title|rep:excerpt(.))", javax.jcr.query.Query.SQL);
			QueryResult eresult = eq.execute();
			for (RowIterator it = eresult.getRows(); it.hasNext(); ) {
			    Row r = it.nextRow();
			    Value excerpt = r.getValue("rep:excerpt(.)");
			    x=x+"--"+excerpt.toString()+" *** ";
			}
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		*/

		while (iterator.hasNext()) {

			Hit singleHit = iterator.next();
			JSONObject jsonObject = new JSONObject();
			try {
				String excerpt=singleHit.getExcerpt();
				if(excerpt.equals(""))
					excerpt="-Empty content-";
				
				jsonObject.put("title", singleHit.getTitle());
				jsonObject.put("path", singleHit.getPath());
				jsonObject.put("excerpts", excerpt);
				jsonObject.put("offset", offset);
				jsonObject.put("limit", limit);
				jsonObject.put("total", result.getTotalMatches());

				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				LOGGER.error("Error in JSON " + e.getMessage());
			} catch (RepositoryException e) {
				LOGGER.error("Error in Repository Access " + e.getMessage());
			}
			
		}
		
		LOGGER.info("Search JSON Array" + jsonArray.toString());
		return jsonArray;

	}

}
