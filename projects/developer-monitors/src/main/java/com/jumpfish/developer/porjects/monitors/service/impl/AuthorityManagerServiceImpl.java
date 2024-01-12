package com.jumpfish.developer.porjects.monitors.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import com.jumpfish.developer.porjects.monitors.domain.authority.entity.ProjectRolePO;
import com.jumpfish.developer.porjects.monitors.domain.authority.mapper.ProjectRoleGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.OperationProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.enums.IsDeleteEnum;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;
import com.jumpfish.developer.porjects.monitors.service.AuthorityManagerService;
import com.jumpfish.developer.porjects.monitors.utils.uid.SnowflakeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class AuthorityManagerServiceImpl implements AuthorityManagerService {

    private final ProjectRoleGeneratorMapper projectRoleGeneratorMapper;

    public AuthorityManagerServiceImpl(ProjectRoleGeneratorMapper projectRoleGeneratorMapper) {
        this.projectRoleGeneratorMapper = projectRoleGeneratorMapper;
    }

    @Override
    public Result<Page> selectRoles(PageLayer pageLayer) {
        Page page = new Page<>(pageLayer.getPageNo(), pageLayer.getPageSize());
        page = projectRoleGeneratorMapper.selectPage(page, Wrappers.<ProjectRolePO>lambdaQuery());
        return ResultFactory.successOf(page);
    }

    @Override
    public Result addRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO) {

        Date current_date = new Date();
        ProjectRolePO role = new ProjectRolePO();
        role.setId(SnowflakeFactory.getId());
        role.setCode(operationProjectRoleVO.getCode());
        role.setParent_code(operationProjectRoleVO.getParent_code());
        role.setIsDelete(IsDeleteEnum.NOT_DELETE.getValue());
        role.setCreateTime(current_date);
        role.setUpdateTime(current_date);
        role.setOperatorTime(current_date);

        int count = projectRoleGeneratorMapper.insert(role);
        return ResultFactory.successOf();
    }

}
