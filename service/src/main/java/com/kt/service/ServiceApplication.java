package com.kt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kimkyungkuk
 * JWT 기반 로그인, oAuth 2.0 기능 개발
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}

@RefreshScope
@RestController	
class MessageRestController {
	@Value("${message:Hello default}") 
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
    
    @RequestMapping("/greeting")
    String greeting() {
        return "greeting service";
    }
}
