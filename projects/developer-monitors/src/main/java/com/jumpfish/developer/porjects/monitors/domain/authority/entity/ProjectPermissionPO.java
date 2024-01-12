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
@Table(name = "project_permission")
@TableName(value = "project_permission")
@org.hibernate.annotations.Table(appliesTo = "project_permission", comment = "项目权限表")
public class ProjectPermissionPO {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(64) comment '权限名称'")
    private String name;

    @Column(name = "url", nullable = false, columnDefinition = "varchar(64) comment '权限链接'")
    private String url;

    @Column(name = "picture_url", nullable = false, columnDefinition = "varchar(64) comment '权限图片链接'")
    private String picture_url;

    @Column(name = "parent_id", nullable = true, columnDefinition = "bigint comment '权限父级主键'")
    private String parent_id;

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
