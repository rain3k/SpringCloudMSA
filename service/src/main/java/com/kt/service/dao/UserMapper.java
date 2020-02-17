package com.kt.service.dao;

import java.util.List;

import com.kt.service.dto.Member;

public interface UserMapper {
    public Member readUser(String username);
    public List<String> readAuthority(String username);
}
