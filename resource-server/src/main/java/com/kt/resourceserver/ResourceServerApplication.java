package com.kt.resourceserver;

import java.security.Principal;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}

	@GetMapping("/whoami") 
    @PreAuthorize("#oauth2.hasScope('openid')") 
    public String whoami() {
        return "Resource Server!";
    }

    @GetMapping("/principal")
    public String principal(@AuthenticationPrincipal Principal name) {
        Optional<Principal> opt = Optional.ofNullable(name);

        if(opt.isPresent())
            return opt.get().getName();

        return "No Principal";
    }
}

@Configuration
class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/error","/whoami","/principal").permitAll();
    }
}
