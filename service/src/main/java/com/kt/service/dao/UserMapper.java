package com.kt.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kt.service.dto.Member;

@Mapper
@Repository
public interface UserMapper {
    public Member readUser(String username);
    public List<String> readAuthority(String username);
}
