package com.kt.service.config.security;

import org.springframework.security.core.userdetails.User;

import com.kt.service.dto.Member;

public class SecurityMember extends User{
	 
    private static final long serialVersionUID = 1L;
    
    private String ip;
    
    public SecurityMember(Member member) {
        super(member.getUsername(), member.getPassword(), member.getAuthorities());
    }
 
    public String getIp() {
        return ip;
    }
 
    public void setIp(String ip) {
        this.ip = ip;
    }
 
}
