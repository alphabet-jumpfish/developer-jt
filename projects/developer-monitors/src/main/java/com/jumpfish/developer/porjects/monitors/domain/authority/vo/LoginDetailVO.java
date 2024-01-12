package com.jumpfish.developer.porjects.monitors.domain.authority.vo;

import lombok.Data;

@Data
public class LoginDetailVO {
    private String access_token;
    private long expires_in;
    private String refresh_token;
    private Long userId;
    private String loginUsername;
}
