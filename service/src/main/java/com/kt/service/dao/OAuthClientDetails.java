package com.kt.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OAuthClientDetails {
	public int insert (Map<String,Object> params);
	public List<Map<String, Object>> select(Map<String,Object> params);
}
