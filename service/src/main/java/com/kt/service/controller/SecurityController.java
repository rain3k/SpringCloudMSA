package com.kt.service.controller;

import java.net.URI;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class SecurityController {

	@Autowired
	RestTemplate restTemplate;

	@ResponseBody
	@PostMapping("/access_token")
	public String index(@RequestParam Map<String, Object> params) {
		String uriString = "http://localhost:8080/oauth/token";
		URI uri = URI.create(uriString);
		RequestEntity<Void> requestEntity = RequestEntity.post(uri)
				.header("Authorization", "Basic " + Base64.encodeBase64String((params.get("client") + ":" + params.get("client_secret")).getBytes()))
				.build();
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		String body = response.getBody();
		return body;
	}

}
