package com.kt.service.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessConfirmationController {
	
	private ClientDetailsService clientDetailsService;

//	@RequestMapping("/oauth/confirm_access")
//	public ModelAndView getAccessConfirmation(@RequestParam Map<String, Object> clientAuth) throws Exception {
//		ClientDetails client = clientDetailsService.loadClientByClientId((String) clientAuth.get("client_id"));
//		TreeMap<String, Object> model = new TreeMap<String, Object>();
//		model.put("auth_request", clientAuth);
//		model.put("client", client);
//		return new ModelAndView("access_confirmation", model);
//	}

	@Autowired
	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}
}
