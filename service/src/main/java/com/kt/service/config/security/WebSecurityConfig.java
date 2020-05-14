package com.kt.service.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.service.config.security.auth.ajax.AjaxAuthenticationProvider;
import com.kt.service.config.security.auth.ajax.AjaxLoginProcessingFilter;
import com.kt.service.config.security.auth.jwt.JwtAuthenticationProvider;
import com.kt.service.config.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.kt.service.config.security.auth.jwt.SkipPathRequestMatcher;
import com.kt.service.config.security.auth.jwt.tokenExtractor.TokenExtractor;

/**
 * @author kimkyungkuk
 * 웹서버 설정
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
	public static final String AUTHENTICATION_OAUTH_URL = "/oauth/**";
	public static final String AUTHENTICATION_URL = "/api/auth/login";
	public static final String REFRESH_TOKEN_URL = "/api/auth/token";
	public static final String API_ROOT_URL = "/api/**";
	public static final String PROMETHEUS_URL = "/actuator/prometheus";

	@Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired private AuthenticationSuccessHandler successHandler;
    @Autowired private AuthenticationFailureHandler failureHandler;
    @Autowired private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired private TokenExtractor tokenExtractor;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private ObjectMapper objectMapper;
    
	/**
	 * JWT기반으로 인증처리 할 것이기 때문에 CSRF 체크할 필요 없음. 
	 * 에러 : authenticationEntryPointd에서 처리
	 * 미인증 접근 허가 : /api/auth/login, /api/auth/token, /console
	 * 인증 접근 허가 : /api/**, /oauth/**
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> permitAllEndpointList = Arrays.asList(PROMETHEUS_URL,AUTHENTICATION_URL, REFRESH_TOKEN_URL, "/console");

		http.csrf().disable() 
				.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()])).permitAll()
				.antMatchers(API_ROOT_URL).authenticated()
				.antMatchers(AUTHENTICATION_OAUTH_URL).authenticated()
				.and().addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildAjaxLoginProcessingFilter(AUTHENTICATION_URL),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList, API_ROOT_URL),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList, AUTHENTICATION_OAUTH_URL),
						UsernamePasswordAuthenticationFilter.class);
	}
	
	/**
	 * Ajax 로그인시 인증 정보 추출 후 인증 객체 생성  
	 * @param loginEntryPoint
	 * @return
	 * @throws Exception
	 */
	protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    /**
     * Header에 JWT Token값을 이용하여 인증 객체 생성
     * @param pathsToSkip
     * @param pattern
     * @return
     * @throws Exception
     */
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    /**
     * Ajax,JWT 인증 처리하는 Provider Manager에 등록
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(ajaxAuthenticationProvider);
    	auth.authenticationProvider(jwtAuthenticationProvider);
    }

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}