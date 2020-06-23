package com.kt.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.service.config.security.SecurityMember;
import com.kt.service.dao.AuthoritiesMapper;
import com.kt.service.dao.UsersMapper;
import com.kt.service.dto.Member;

/**
 * @author kimkyungkuk
 * Spring Security 인증을 위해 UserDetailsService interface 정의
 */
@Service
@Transactional
public class UserService implements UserDetailsService{
	private static final String ROLE_PREFIX = "ROLE_";

	@Autowired
	UsersMapper userMapper;
	
	@Autowired
	AuthoritiesMapper authoritiesMapper;

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

	public void usersUpdate(Map<String, Object> params) {
		userMapper.usersUpdate(params);
	}

	public List<Map<String,Object>> usersList(Map<String, Object> params) {
		return userMapper.usersList(params);
	}

	public boolean usersInsert(Map<String, Object> params) {
		return userMapper.usersInsert(params) == 1 && authoritiesMapper.authoritiesInsert(params) == 1;
	}
}
