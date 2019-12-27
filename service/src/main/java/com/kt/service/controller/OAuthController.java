package com.kt.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.service.service.OAuthService;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class OAuthController {

	@Autowired
	OAuthService oAuthService;
	
    @GetMapping("/oAuth/list")
    @ResponseBody
    public List<Map<String, Object>> oAuthList(@RequestParam Map<String, Object> params) {
    	List<Map<String, Object>> oAuthClientDetailsList = oAuthService.oAuthClientDetailsList(params);
        return oAuthClientDetailsList;
    }
}
