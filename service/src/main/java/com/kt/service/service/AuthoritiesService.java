package com.kt.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.service.dao.AuthoritiesMapper;


@Service
public class AuthoritiesService{

	@Autowired
	AuthoritiesMapper authoritiesMapper;

	public void authoritiesUpdate(Map<String, Object> params) {
		authoritiesMapper.authoritiesUpdate(params);
	}

	public List<Map<String,Object>> authoritiesList(Map<String, Object> params) {
		return authoritiesMapper.authoritiesList(params);
	}

	public int authoritiesInsert(Map<String, Object> params) {
		return authoritiesMapper.authoritiesInsert(params);
	}
}
