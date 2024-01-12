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
// 标签必填
@Table(name = "project_role")
@TableName(value = "project_role")
@org.hibernate.annotations.Table(appliesTo = "project_role", comment = "项目角色表")
public class ProjectRolePO {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Long id;

    @Column(name = "code", nullable = false, columnDefinition = "varchar(64) comment '角色编码'")
    private String code;

    @Column(name = "parent_code", nullable = false, columnDefinition = "varchar(64) comment '角色父类编码'")
    private String parent_code;

    @Column(name = "type", nullable = true, columnDefinition = "int(2) comment '角色 1.系统 2.客户端'")
    private Integer type;

    @Column(name = "status", nullable = true, columnDefinition = "int(2) comment '角色状态 1.有效 2.无效'")
    private Integer status;

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
