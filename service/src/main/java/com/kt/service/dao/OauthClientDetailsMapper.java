package com.kt.service.dao;

import java.util.List;
import java.util.Map;

public interface OauthClientDetailsMapper {
	public List<Map<String, Object>> oauthClientDetailsList(Map<String, Object> params);
	public int oauthClientDetailsUpdate(Map<String, Object> params);
	public int oauthClientDetailsInsert(Map<String, String> params);
}
