package com.jumpfish.developer.porjects.monitors.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import com.jumpfish.developer.porjects.monitors.domain.user.mapper.UserManagerGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.enums.IsDeleteEnum;
import com.jumpfish.developer.porjects.monitors.service.CustomTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class Oauth2UserDetailsServiceImpl implements UserDetailsService {

    private final CustomTokenService customTokenService;

    public Oauth2UserDetailsServiceImpl(CustomTokenService customTokenService) {
        this.customTokenService = customTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OauthUserDTO user = customTokenService.convertUser(username);
        return user;
    }
}
