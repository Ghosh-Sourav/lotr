package com.tcs.services.impl;

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
import com.tcs.queryBuilderDemo.SearchService;
import com.tcs.queryBuilderDemo.SearchServiceImpl;
import com.tcs.services.TagService;

@Component
@Service(value = TagService.class)
public class TagServiceImpl implements TagService {

	@Reference
	QueryBuilder queryBuilder;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchServiceImpl.class);

	
	public JSONArray getAllTags(String tagGroup, Session session) {

		JSONArray jsonArray = new JSONArray();

		Map<String, String> map = new HashMap<String, String>();
		map.put("path", "/etc/tags/"+tagGroup);
		map.put("p.limit", "-1");
	
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
				jsonObject.put("tagid", singleHit.getNode().getPath().replace("/etc/tags/"+tagGroup+"/", ""));

			} catch (JSONException e) {
				LOGGER.error("Error in JSON " + e.getMessage());
			} catch (RepositoryException e) {
				LOGGER.error("Error in Repository Access " + e.getMessage());
			}
			
			jsonArray.put(jsonObject);

		}
		
		return jsonArray;
	}

}
