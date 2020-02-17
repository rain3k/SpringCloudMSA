package com.kt.service.dto;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Member {
	@NotNull
	private String username;
	private String password;
	private String name;
	private Collection<? extends GrantedAuthority> authorities;
}
