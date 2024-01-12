package com.jumpfish.developer.porjects.monitors.service;

import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;

public interface CustomTokenService {
    OauthUserDTO convertUser(String username);

}
