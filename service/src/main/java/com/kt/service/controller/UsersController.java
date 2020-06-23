package com.kt.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/service")
public class UsersController {
	
	@Autowired
	UserService userService;

	@PostMapping("/api/users/list")
    public List<Map<String,Object>> usersList(@RequestBody(required = false)Map<String, Object> params) {
        return userService.usersList(params);
    }

	@PostMapping("/api/users/update")
    public Map<String,Object> usersUpdate(@RequestBody(required = false) Map<String, Object> params) {
    	userService.usersUpdate(params);
        return params;
    }

	@PostMapping("/api/users/insert")
    public Map<String,Object> usersInsert(@RequestBody Map<String, Object> params) {
		log.debug("params:"+params.toString());
    	userService.usersInsert(params);
        return params;
    }
}
