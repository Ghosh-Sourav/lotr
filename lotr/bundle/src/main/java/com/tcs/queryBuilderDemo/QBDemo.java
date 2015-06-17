package com.tcs.queryBuilderDemo;

import java.util.HashMap;
import java.util.Map;




import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.sling.commons.json.JSONObject;
/*
import com.day.cq.search.*;
import com.day.cq.search.impl.builder.QueryBuilderImpl;
import com.day.cq.search.result.SearchResult;
*/
public class QBDemo {

	private static int numberOfElemInOnePage = 2;
	
	public static JSONObject getJSONObject(String q, String pageNumber)
	{
		JSONObject jsonObject=new JSONObject();
		Map<String, String> map=new HashMap<String, String>();
		map.put("type", "cq:Page");
		map.put("property", "jcr:content/jcr:title");
		map.put("property.value", q);
		map.put("group.1_path", "/content/lord-of-the-rings/en/middle-earth/");
		map.put("group.p.or", "true");
		
		int pgNo=Integer.parseInt(pageNumber);
		int offsetNo=(pgNo-1)*numberOfElemInOnePage;
		
		map.put("p.offset",""+offsetNo);
		map.put("p.limit", ""+numberOfElemInOnePage);
		
		
		/*QueryBuilder builder=new QueryBuilderImpl();
		Query query = builder.createQuery(PredicateGroup.create(map),null);
		
		
		 */
		return jsonObject;
	}
	
	
	
	
	public static JSONObject getJSONObject(String q){
		return getJSONObject(q,"1");
	}
}
