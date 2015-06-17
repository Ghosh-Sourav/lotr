package com.tcs.queryBuilderDemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

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
@Service(value = AltSearchService.class)
public class AltSearchServiceImpl implements AltSearchService {

	@Reference
	QueryBuilder queryBuilder;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AltSearchServiceImpl.class);

	public JSONArray getResults(String limit, String offset,
			String searchPath , Session session) {

		JSONArray jsonArray = new JSONArray();

		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "cq:Page");
		map.put("path", searchPath);
		map.put("p.offset", offset);
		map.put("p.limit", limit);
		
		
		
		LOGGER.info("Inside the getResults Method");

		Query query = queryBuilder
				.createQuery(PredicateGroup.create(map), session);

		SearchResult result = query.getResult();

		List<Hit> results = result.getHits();
		
		Iterator<Hit> iterator = results.iterator();
		
		LOGGER.info("The Result Size is " + results.size());

		while (iterator.hasNext()) {

			Hit singleHit = iterator.next();
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("title", singleHit.getTitle());
				jsonObject.put("path", singleHit.getPath());
			

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
