package com.sanver.trials.mobileappws.security;

public class SecurityConstants {
    public static final long EXPIRATION_TIME=864_000_000; // 10 days value in milliseconds
    public static final String TOKEN_PREFIX="bearer ";
    public static final String HEADER_STRING_TOKEN ="Authorization";
    public static final String SIGN_UP_URL = "/users/login";
    public static final String TOKEN_SECRET = "kd832lc3d34ka";
    public static final String HEADER_STRING_USER_ID="UserID";
}
