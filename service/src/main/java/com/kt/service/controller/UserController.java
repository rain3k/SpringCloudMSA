package com.kt.service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kt.service.dto.Member;
import com.kt.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;

    @GetMapping("/api/user")
    @ResponseBody
    public String userView(@RequestParam Map<String, Object> params) {
        return "/user/index";
    }
    
    @PostMapping("/api/user/add")
    public String userAdd(Member member) {
    	return "/user/index";
    }

    @GetMapping("/api/user/list")
    public String userList(@RequestParam Map<String, Object> params) {
        return "/user/list";
    }
    

    @GetMapping("/api/user/update")
    public String userUpdate(@RequestParam Map<String, Object> params) {
    	userService.userUpdate(params);
        return "/user/list";
    }
}
