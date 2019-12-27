package com.kt.service.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.util.logging.Slf4j;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/user")
    public String userView(@RequestParam Map<String, Object> params) {
        return "/user/index";
    }
}
