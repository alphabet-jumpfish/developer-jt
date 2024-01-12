package com.jumpfish.developer.porjects.monitors.access;

import lombok.Data;

@Data
public class AccessToken {
    private String access_token;
    private long expires_in;
    private String token_type;
    private String refresh_token;
    private String scope;
    private String jti;
}
