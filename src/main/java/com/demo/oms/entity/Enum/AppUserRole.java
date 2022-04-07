package com.demo.oms.entity.Enum;


import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
    ADMIN(Code.ADMIN),
    USER(Code.USER);

    private final String authority;


    AppUserRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}

