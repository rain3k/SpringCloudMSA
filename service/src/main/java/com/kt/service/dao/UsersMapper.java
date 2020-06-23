package com.kt.service.dao;

import java.util.List;
import java.util.Map;

import com.kt.service.dto.Member;

public interface UsersMapper {
    public Member readUser(String username);
    public List<String> readAuthority(String username);
	public List<Map<String, Object>> usersList(Map<String, Object> params);
	public int usersUpdate(Map<String, Object> params);
	public int usersInsert(Map<String, Object> params);
}
