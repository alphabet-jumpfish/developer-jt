package com.jumpfish.developer.porjects.monitors.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jumpfish.developer.porjects.monitors.access.AccessToken;
import com.jumpfish.developer.porjects.monitors.access.OAuth2Api;
import com.jumpfish.developer.porjects.monitors.common.result.DefaultResultCode;
import com.jumpfish.developer.porjects.monitors.common.result.MicroBizException;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.LoginDetailVO;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import com.jumpfish.developer.porjects.monitors.domain.user.mapper.UserManagerGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.domain.user.mapper.UserManagerMapper;
import com.jumpfish.developer.porjects.monitors.domain.user.service.impl.IUserManagerServiceImpl;
import com.jumpfish.developer.porjects.monitors.enums.IsDeleteEnum;
import com.jumpfish.developer.porjects.monitors.service.UserManagerService;
import com.jumpfish.developer.porjects.monitors.utils.des.DesCryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserManagerServiceImpl implements UserManagerService {

    private final UserManagerMapper userManagerMapper;
    private final UserManagerGeneratorMapper userManagerGeneratorMapper;
    private final IUserManagerServiceImpl iUserManagerService;
    private final OAuth2Api oAuth2Api;

    public UserManagerServiceImpl(UserManagerMapper userManagerMapper, UserManagerGeneratorMapper userManagerGeneratorMapper, IUserManagerServiceImpl iUserManagerService, OAuth2Api oAuth2Api) {
        this.userManagerMapper = userManagerMapper;
        this.userManagerGeneratorMapper = userManagerGeneratorMapper;
        this.iUserManagerService = iUserManagerService;
        this.oAuth2Api = oAuth2Api;
    }

    @Override
    public Result<UserPO> users() {
        List<UserPO> userPOS = userManagerMapper.selectUsers();
        List<UserPO> userPOS1 = iUserManagerService.list(Wrappers.<UserPO>lambdaQuery());
        log.info("用户信息:{}", JSONObject.toJSONString(userPOS));
        log.info("用户信息S:{}", JSONObject.toJSONString(userPOS1));
        return null;
    }

    @Override
    public Result<LoginDetailVO> login(String username, String password) {
        UserPO user = userManagerGeneratorMapper.selectOne(
                Wrappers.<UserPO>lambdaQuery().eq(UserPO::getLoginUsername, username)
                        .eq(UserPO::getIsDelete, IsDeleteEnum.NOT_DELETE.getValue())
                        .last("LIMIT 1")
        );
        final String login_username = user.getLoginUsername();
        // 登陆操作
        AccessToken accessToken = null;
        try {
            String encodePassword = DesCryptUtils.encode(password);
            if (!encodePassword.equals(user.getLoginPassword())) {
                throw new MicroBizException(DefaultResultCode.PASSWORD_ERROR.getCode(), "密码错误");
            }
            accessToken = oAuth2Api.defaultGetToken(login_username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (accessToken != null && user != null) {
            LoginDetailVO detail = new LoginDetailVO();
            detail.setAccess_token(accessToken.getAccess_token());
            detail.setRefresh_token(accessToken.getRefresh_token());
            detail.setExpires_in(accessToken.getExpires_in());
            detail.setUserId(user.getId());
            detail.setLoginUsername(user.getLoginUsername());
            return ResultFactory.successOf(detail);
        }

        return ResultFactory.businessErrorOf(null);
    }
}
