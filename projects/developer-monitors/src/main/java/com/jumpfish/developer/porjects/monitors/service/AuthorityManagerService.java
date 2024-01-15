package com.jumpfish.developer.porjects.monitors.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.domain.authority.entity.ProjectRolePO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.OperationProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.ProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;

public interface AuthorityManagerService {

    Result<Page<ProjectRoleVO>> selectRoles(PageLayer pageLayer);

    Result<ProjectRoleVO> addRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO);

    Result<ProjectRoleVO> updateRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO);

    Result deleteRole(CurrentUser currentUser, Long id);

}
