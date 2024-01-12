package com.jumpfish.developer.porjects.monitors.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jumpfish.developer.porjects.monitors.authority.domain.user.OauthUserDTO;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import com.jumpfish.developer.porjects.monitors.domain.user.mapper.UserManagerGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.enums.IsDeleteEnum;
import com.jumpfish.developer.porjects.monitors.service.CustomTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomTokenServiceImpl implements CustomTokenService {

    private final UserManagerGeneratorMapper userManagerGeneratorMapper;

    public CustomTokenServiceImpl(UserManagerGeneratorMapper userManagerGeneratorMapper) {
        this.userManagerGeneratorMapper = userManagerGeneratorMapper;
    }

    @Override
    public OauthUserDTO convertUser(String username) {
        UserPO user = userManagerGeneratorMapper.selectOne(
                Wrappers.<UserPO>lambdaQuery().eq(UserPO::getLoginUsername, username)
                        .eq(UserPO::getIsDelete, IsDeleteEnum.NOT_DELETE.getValue())
                        .last("LIMIT 1")
        );
        OauthUserDTO oauthUserDTO = new OauthUserDTO(username, user.getLoginPassword(), Collections.emptyList());
        oauthUserDTO.setLoginName(user.getLoginUsername());
        oauthUserDTO.setUserId(user.getId());
        return oauthUserDTO;
    }
}
