package com.kt.service.dao;

import java.util.List;
import java.util.Map;

public interface AuthoritiesMapper {
	public List<Map<String, Object>> authoritiesList(Map<String, Object> params);
	public int authoritiesUpdate(Map<String, Object> params);
	public int authoritiesInsert(Map<String, Object> params);
}
