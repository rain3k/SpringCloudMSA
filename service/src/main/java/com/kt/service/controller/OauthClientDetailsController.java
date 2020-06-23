package com.kt.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.service.service.OauthClientDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/service")
public class OauthClientDetailsController {
	
	@Autowired
	OauthClientDetailsService oauthClientDetailsService;

	@PostMapping("/api/oauthClientDetailss/list")
    public List<Map<String,Object>> oauthClientDetailsList(@RequestBody(required = false) Map<String, Object> params) {
        return oauthClientDetailsService.oauthClientDetailsList(params);
    }

	@PostMapping("/api/oauthClientDetailss/update")
    public Map<String,Object> oauthClientDetailssUpdate(@RequestBody Map<String, Object> params) {
    	oauthClientDetailsService.oauthClientDetailsUpdate(params);
        return params;
    }

    @PostMapping("/api/oauthClientDetailss/insert")
    public Map<String,String> oauthClientDetailssInsert(@RequestBody Map<String, String> params) {
    	oauthClientDetailsService.oauthClientDetailsInsert(params);
        return params;
    }
}
