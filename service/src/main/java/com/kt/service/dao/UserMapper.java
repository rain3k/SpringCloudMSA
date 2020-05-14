package com.kt.service.dao;

import java.util.List;
import java.util.Map;

import com.kt.service.dto.Member;

public interface UserMapper {
    public Member readUser(String username);
    public List<String> readAuthority(String username);
	public void userUpdate(Map<String, Object> params);
}
