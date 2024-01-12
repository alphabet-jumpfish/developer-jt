package com.jumpfish.developer.porjects.monitors.authority.domain.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OauthMenuDTO implements Serializable {

    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父编码
     */
    private String pcode;

    /**
     * 状态 1：有效 2：无效
     */
    private String status;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 菜单项URL
     */
    private String menuUrl;

    /**
     * 图片URL
     */
    private String picUrl;

    /**
     * 排序
     */
    private Integer seqNum;

    /**
     * 系统编码
     */
    private String sysCode;

    /**
     * 系统版本
     */
    private String sysVersion;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除标识 1未删除 2已删除
     */
    private String isDelete;

    /**
     * 平台类型 1.web 2.公众号
     */
    private Integer platformType;
}
