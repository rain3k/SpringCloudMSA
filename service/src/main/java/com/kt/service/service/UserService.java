package com.kt.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kt.service.config.security.SecurityMember;
import com.kt.service.dao.UserMapper;
import com.kt.service.dto.Member;

/**
 * @author kimkyungkuk
 * Spring Security 인증을 위해 UserDetailsService interface 정의
 */
@Service
public class UserService implements UserDetailsService{
	private static final String ROLE_PREFIX = "ROLE_";

	@Autowired
	UserMapper userMapper;

	@Override
	public SecurityMember loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = userMapper.readUser(username);
		if (member != null) {
			member.setAuthorities(makeGrantedAuthority(userMapper.readAuthority(username)));
		}
		return new SecurityMember(member);
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<String> roles) {
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
		return list;
	}
}
