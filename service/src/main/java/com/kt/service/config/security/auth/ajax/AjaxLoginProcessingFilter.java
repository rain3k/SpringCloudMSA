package com.kt.service.config.security.auth.ajax;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kimkyungkuk
 * Ajax로 요청시 처리 : Header X-Requested-With의 키값 XMLHttpRequest 확인
 * 요청에서 username와 password를 추출하여 인증객체 생성 
 */
@Slf4j
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    private final ObjectMapper objectMapper;
    
    public AjaxLoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler, 
            AuthenticationFailureHandler failureHandler, ObjectMapper mapper) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
    	log.debug("AjaxLoginProcessingFilter-attemptAuthentication:"+request.getMethod());
//        if (!HttpMethod.POST.name().equals(request.getMethod()) ) {
//            if(logger.isDebugEnabled()) {
//                logger.debug("Authentication method not supported. Request method: " + request.getMethod());
//            }
//            throw new AuthMethodNotSupportedException("Authentication method not supported");
//        }

        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        
        if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}