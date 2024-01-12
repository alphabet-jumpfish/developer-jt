package com.jumpfish.developer.porjects.monitors.domain.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "project_role_permission")
@TableName(value = "project_role_permission")
@org.hibernate.annotations.Table(appliesTo = "project_role_permission", comment = "项目角色权限关联表")
public class ProjectRolePermissionPO {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Long id;

    @Column(name = "role_id", nullable = true, columnDefinition = "bigint comment '角色主键'")
    private Long role_id;

    @Column(name = "permission_id", nullable = true, columnDefinition = "bigint comment '权限主键'")
    private Long permission_id;

    // 通用属性
    @Column(name = "operator_id", nullable = true, columnDefinition = "bigint comment '操作人主键'")
    private Long operatorId;
    @Column(name = "operator_time", nullable = true, columnDefinition = "datetime comment '操作时间'")
    private Date operatorTime;
    @Column(name = "update_id", nullable = true, columnDefinition = "bigint comment '修改人主键'")
    private Long updateId;
    @Column(name = "update_time", nullable = true, columnDefinition = "datetime comment '修改时间'")
    private Date updateTime;
    @Column(name = "create_id", nullable = true, columnDefinition = "bigint comment '创建人主键'")
    private Long createId;
    @Column(name = "create_time", nullable = true, columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
    @Column(name = "is_delete", nullable = false, columnDefinition = "varchar(1) comment '删除标识 1.未删除 2.已删除'")
    private String isDelete;

}
