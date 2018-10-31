package com.sanver.trials.mobileappws.security;

import com.sanver.trials.mobileappws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days value in milliseconds
    public static final String TOKEN_PREFIX = "bearer ";
    public static final String HEADER_STRING_TOKEN = "Authorization";
    public static final String LOGIN_URL = "/users/login";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET_PROPERTY_NAME = "tokenSecret";
    public static final String HEADER_STRING_USER_ID = "UserID";

    public static String getTokenSecret() {
        AppProperties appProperties = SpringApplicationContext.getBean(AppProperties.class);
        return appProperties.getTokenSecret();
    }
}
