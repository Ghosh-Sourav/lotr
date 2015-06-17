package com.tcs.services;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;

public interface TagService {
	public JSONArray getAllTags(String tagGroup, Session session);
}
