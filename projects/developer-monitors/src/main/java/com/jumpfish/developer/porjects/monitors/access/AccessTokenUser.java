package com.jumpfish.developer.porjects.monitors.access;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccessTokenUser implements Serializable {
    private List authorities;
    private String username;
    private Long userId;
    private String labelCode;
    private String fullName;
}
