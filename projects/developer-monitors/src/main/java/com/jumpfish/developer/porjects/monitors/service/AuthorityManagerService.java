package com.jumpfish.developer.porjects.monitors.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.domain.authority.entity.ProjectRolePO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.OperationProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;

public interface AuthorityManagerService {

    Result<Page> selectRoles(PageLayer pageLayer);

    Result addRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO);
}
