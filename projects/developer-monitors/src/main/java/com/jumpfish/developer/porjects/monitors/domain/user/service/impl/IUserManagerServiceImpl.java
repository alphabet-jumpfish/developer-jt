package com.jumpfish.developer.porjects.monitors.domain.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import com.jumpfish.developer.porjects.monitors.domain.user.mapper.UserManagerGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.domain.user.service.IUserManagerService;
import org.springframework.stereotype.Service;

@Service
public class IUserManagerServiceImpl extends ServiceImpl<UserManagerGeneratorMapper, UserPO> implements IUserManagerService {

}
