package com.jumpfish.developer.porjects.monitors.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jumpfish.developer.porjects.monitors.access.CurrentUser;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import com.jumpfish.developer.porjects.monitors.domain.authority.entity.ProjectRolePO;
import com.jumpfish.developer.porjects.monitors.domain.authority.mapper.ProjectRoleGeneratorMapper;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.OperationProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.domain.authority.vo.ProjectRoleVO;
import com.jumpfish.developer.porjects.monitors.enums.IsDeleteEnum;
import com.jumpfish.developer.porjects.monitors.layerpage.PageLayer;
import com.jumpfish.developer.porjects.monitors.service.AuthorityManagerService;
import com.jumpfish.developer.porjects.monitors.utils.uid.SnowflakeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
public class AuthorityManagerServiceImpl implements AuthorityManagerService {

    private final ProjectRoleGeneratorMapper projectRoleGeneratorMapper;

    public AuthorityManagerServiceImpl(ProjectRoleGeneratorMapper projectRoleGeneratorMapper) {
        this.projectRoleGeneratorMapper = projectRoleGeneratorMapper;
    }

    @Override
    public Result<Page<ProjectRoleVO>> selectRoles(PageLayer pageLayer) {
        /**
         * 分页查询
         */
        Page page = new Page<>(pageLayer.getPageNo(), pageLayer.getPageSize());
        page = projectRoleGeneratorMapper.selectPage(page, Wrappers.<ProjectRolePO>lambdaQuery());

        /**
         * convert
         * 转换函数
         */
        Function<ProjectRolePO, ProjectRoleVO> convert = role -> {
            ProjectRoleVO roleVO = new ProjectRoleVO();
            roleVO.setId(role.getId());
            roleVO.setCode(role.getCode());
            roleVO.setParent_code(role.getParent_code());
            roleVO.setStatus(role.getStatus());
            roleVO.setIsDelete(role.getIsDelete());
            return roleVO;
        };

        /**
         * 分页实体对象转换
         */
        IPage<ProjectRoleVO> newPage = page.convert(convert);
        return ResultFactory.successOf(newPage);
    }

    @Override
    public Result<ProjectRoleVO> addRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO) {

        /**
         * 前置判断
         *  条件一: code编码是否存在
         *  查询激活操作角色库内数据
         * 其他:
         *  新增流程
         */
        final ProjectRolePO role = projectRoleGeneratorMapper.selectOne(
                Wrappers.<ProjectRolePO>lambdaQuery().eq(ProjectRolePO::getCode, operationProjectRoleVO.getCode())
        );
        if (role != null) {
            operationProjectRoleVO.setId(role.getId());
            operationProjectRoleVO.setIsDelete(IsDeleteEnum.NOT_DELETE.getValue());
            Result<ProjectRoleVO> result = this.updateRole(currentUser, operationProjectRoleVO);
            return result;
        }

        /**
         * 转换实体对象入参数
         */
        Date current_date = new Date();
        final ProjectRolePO addRole = new ProjectRolePO();
        addRole.setId(SnowflakeFactory.getId());
        addRole.setCode(operationProjectRoleVO.getCode());
        addRole.setParent_code(operationProjectRoleVO.getParent_code());
        addRole.setCreateTime(current_date);
        addRole.setUpdateTime(current_date);
        addRole.setOperatorTime(current_date);
        addRole.setIsDelete(IsDeleteEnum.NOT_DELETE.getValue());

        /**
         * 插入操作
         */
        int count = projectRoleGeneratorMapper.insert(addRole);

        /**
         * convert
         * 转换函数
         */
        Function<ProjectRolePO, ProjectRoleVO> convert = r -> {
            ProjectRoleVO roleVO = new ProjectRoleVO();
            roleVO.setId(r.getId());
            roleVO.setCode(r.getCode());
            roleVO.setParent_code(r.getParent_code());
            roleVO.setStatus(r.getStatus());
            roleVO.setIsDelete(r.getIsDelete());
            return roleVO;
        };

        ProjectRoleVO roleVo = convert.apply(addRole);
        return ResultFactory.successOf(roleVo);
    }

    @Override
    public Result<ProjectRoleVO> updateRole(CurrentUser currentUser, OperationProjectRoleVO operationProjectRoleVO) {

        /**
         * 前置属性声明
         */
        final Long id = operationProjectRoleVO.getId();
        final ProjectRolePO role = projectRoleGeneratorMapper.selectById(id);

        /**
         * 属性修改函数
         */
        Function<ProjectRolePO, ProjectRolePO> updateConvert = r -> {
            Date current_date = new Date();
            if (StringUtils.isNotBlank(operationProjectRoleVO.getCode())) {
                r.setCode(operationProjectRoleVO.getCode());
            }
            if (StringUtils.isNotBlank(operationProjectRoleVO.getParent_code())) {
                r.setParent_code(operationProjectRoleVO.getParent_code());
            }
            if (StringUtils.isNotBlank(operationProjectRoleVO.getIsDelete())) {
                r.setIsDelete(operationProjectRoleVO.getIsDelete());
            }
            r.setUpdateTime(current_date);
            r.setOperatorTime(current_date);
            return r;
        };

        /**
         * 数据库属性修改
         */
        final ProjectRolePO updateRole = updateConvert.apply(role);
        projectRoleGeneratorMapper.updateById(updateRole);

        /**
         * convert
         * 转换函数
         */
        Function<ProjectRolePO, ProjectRoleVO> convert = r -> {
            ProjectRoleVO roleVO = new ProjectRoleVO();
            roleVO.setId(r.getId());
            roleVO.setCode(r.getCode());
            roleVO.setParent_code(r.getParent_code());
            roleVO.setStatus(r.getStatus());
            roleVO.setIsDelete(r.getIsDelete());
            return roleVO;
        };

        ProjectRoleVO roleVo = convert.apply(role);

        return ResultFactory.successOf(roleVo);
    }

    @Override
    public Result deleteRole(CurrentUser currentUser, Long id) {
        /**
         * 假删除
         */
        ProjectRolePO role = new ProjectRolePO();
        role.setId(id);
        role.setIsDelete(IsDeleteEnum.DELETED.getValue());
        projectRoleGeneratorMapper.updateById(role);
        return ResultFactory.successOf();
    }

}
