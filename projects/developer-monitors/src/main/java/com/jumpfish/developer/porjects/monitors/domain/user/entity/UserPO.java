package com.jumpfish.developer.porjects.monitors.domain.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "project_user")
@TableName(value = "project_user")
@org.hibernate.annotations.Table(appliesTo = "project_user", comment = "用户表")
public class UserPO {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Long id;

    @Column(name = "login_username", nullable = false, columnDefinition = "varchar(128) comment '登陆名称'")
    private String loginUsername;
    @Column(name = "login_password", nullable = false, columnDefinition = "varchar(128) comment '登陆密码'")
    private String loginPassword;
    @Column(name = "login_telephone", nullable = true, columnDefinition = "varchar(128) comment '登陆手机号'")
    private String loginTelephone;
    @Column(name = "login_email", nullable = true, columnDefinition = "varchar(128) comment '登陆邮箱'")
    private String loginEmail;
    @Column(name = "status", nullable = false, columnDefinition = "int(2) comment '用户状态 1.有效 2.无效'")
    private Integer status;

    // 省-市-区 编码
    @Column(name = "prefix_code", nullable = true, columnDefinition = "varchar(64) comment '省-市-区 编码'")
    private String prefixCode;
    @Column(name = "address", nullable = true, columnDefinition = "varchar(255) comment '详细地址'")
    private String address;
    @Column(name = "full_name", nullable = true, columnDefinition = "varchar(32) comment '全称'")
    private String fullName;
    @Column(name = "short_name", nullable = true, columnDefinition = "varchar(32) comment '昵称'")
    private String shortName;
    @Column(name = "sex", nullable = true, columnDefinition = "int(2) comment '性别（0：表示女 1：表示男）'")
    private Integer sex;
    @Column(name = "latitude", nullable = true, columnDefinition = "varchar(32) comment '用户纬度'")
    private String latitude;
    @Column(name = "longitude", nullable = true, columnDefinition = "varchar(32) comment '用户精度'")
    private String longitude;
    @Column(name = "label_code", nullable = true, columnDefinition = "varchar(32) comment '用户标签编码'")
    private String labelCode;
    @Column(name = "label_name", nullable = true, columnDefinition = "varchar(32) comment '用户标签名字'")
    private String labelName;
    @Column(name = "code", nullable = true, columnDefinition = "varchar(64) comment '用户编码(随机生成)'")
    private String code;

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
