package com.upc.hydroti.security.config;

public class Constants {

    public static final String SIGNUP_URL = "/api/users";
    public static final String TOKEN_URL = "/api/auth/token";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String ROLE_CLAIM = "roles";
    public static final String SECRET = "s3cr3t";

}
