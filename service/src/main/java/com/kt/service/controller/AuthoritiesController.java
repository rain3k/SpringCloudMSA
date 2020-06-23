package com.kt.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.service.service.AuthoritiesService;

@RestController
@RequestMapping("/service")
public class AuthoritiesController {
	
	@Autowired
	AuthoritiesService authoritiesService;

	@PostMapping("/api/authoritiess/list")
    public List<Map<String,Object>> authoritiessList(@RequestParam(required = false)Map<String, Object> params) {
        return authoritiesService.authoritiesList(params);
    }

	@PostMapping("/api/authoritiess/update")
    public Map<String,Object> authoritiessUpdate(@RequestParam Map<String, Object> params) {
    	authoritiesService.authoritiesUpdate(params);
        return params;
    }

	@PostMapping("/api/authoritiess/insert")
    public Map<String,Object> authoritiessInsert(@RequestParam Map<String, Object> params) {
    	authoritiesService.authoritiesInsert(params);
        return params;
    }
}
