package com.kt.service.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuthTestController {

	@RequestMapping("/main")
	public String usersList(@RequestBody(required = false) Map<String, Object> params) {
		return "main";
	}
}
