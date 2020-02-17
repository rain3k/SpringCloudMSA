package com.kt.service.config.security.auth.jwt.tokenExtractor;

public interface TokenExtractor {
    public String extract(String payload);
}
