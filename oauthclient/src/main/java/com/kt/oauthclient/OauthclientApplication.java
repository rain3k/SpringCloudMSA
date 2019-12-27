package com.kt.oauthclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RestController
@EnableOAuth2Client
public class OauthclientApplication {

	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(OauthclientApplication.class, args);
	}

	@GetMapping("/api")
	public String home() {
		log.debug("===================== api =====================");
		return restTemplate.getForObject("http://localhost:8001/whoami", String.class);
	}
}

@Configuration
class DisableSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}

	@Bean
	public RestOperations restTemplate(OAuth2ProtectedResourceDetails resource,
			OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource, oauth2ClientContext);
	}
}