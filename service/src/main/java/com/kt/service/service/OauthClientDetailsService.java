package com.kt.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.service.dao.OauthClientDetailsMapper;



@Service
public class OauthClientDetailsService{

	@Autowired
	OauthClientDetailsMapper oauthClientDetailsMapper;

	public void oauthClientDetailsUpdate(Map<String, Object> params) {
		oauthClientDetailsMapper.oauthClientDetailsUpdate(params);
	}

	public List<Map<String,Object>> oauthClientDetailsList(Map<String, Object> params) {
		return oauthClientDetailsMapper.oauthClientDetailsList(params);
	}

	public int oauthClientDetailsInsert(Map<String, String> params) {
		return oauthClientDetailsMapper.oauthClientDetailsInsert(params);
	}
}
