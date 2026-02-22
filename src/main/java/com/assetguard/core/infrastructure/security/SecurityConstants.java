package com.assetguard.core.infrastructure.security;

public final class SecurityConstants {

    public static final String[] PUBLIC_ENDPOINTS = {
        "/auth/**",
        "/actuator/health",
        "/actuator/health/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/v3/api-docs.yaml",
        "/webjars/**"
    };

    public static final String API_PREFIX = "/api";

    private SecurityConstants() {}
}
