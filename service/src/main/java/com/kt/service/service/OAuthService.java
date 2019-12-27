package com.kt.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.service.dao.OAuthClientDetails;

@Service
public class OAuthService {
	
	@Autowired
	OAuthClientDetails oAuthClientDetails;

	public List<Map<String,Object>> oAuthClientDetailsList(Map<String, Object> params) {
		return oAuthClientDetails.select(params);
	}
}
