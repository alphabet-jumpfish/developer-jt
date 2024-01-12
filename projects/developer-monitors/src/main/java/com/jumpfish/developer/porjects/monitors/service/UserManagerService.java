package com.jumpfish.developer.porjects.monitors.service;

import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.LoginDetailVO;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;

public interface UserManagerService {
    Result<UserPO> users();
    Result<LoginDetailVO> login(String username, String password);
}
