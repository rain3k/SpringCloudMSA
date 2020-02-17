package com.kt.service.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kt.service.dto.Member;

@Controller
public class UserController {
	
    @GetMapping("/user")
    public String userView(@RequestParam Map<String, Object> params) {
        return "/user/index";
    }
    
    @PostMapping("/user/add")
    public String userAdd(Member member) {
    	return "/user/index";
    }

    @GetMapping("/user/list")
    public String userList(@RequestParam Map<String, Object> params) {
        return "/user/list";
    }
}
