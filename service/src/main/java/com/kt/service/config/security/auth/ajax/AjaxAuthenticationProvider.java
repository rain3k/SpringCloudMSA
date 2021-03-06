package com.kt.service.config.security.auth.ajax;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.kt.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kimkyungkuk
 * 인증 객체에서 추출한 username과 password를 이용하여 DB값과 확인 후 권한 부여
 */
@Component
@Slf4j
public class AjaxAuthenticationProvider implements AuthenticationProvider {
	
    private UserService userService;
	private BCryptPasswordEncoder passwordEncoder;

	public AjaxAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
    	this.userService = userService;
    	this.passwordEncoder = passwordEncoder;
	}

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	log.debug("AjaxAuthenticationProvider-authenticate");
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.debug("passwordEncoder : "+passwordEncoder.encode(password));
        UserDetails user = userService.loadUserByUsername(username);
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getAuthorities() == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}