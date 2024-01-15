package com.jumpfish.developer.porjects.monitors.domain.authority.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectRoleVO implements Serializable {

    private Long id;
    private String code;
    private String parent_code;
    private Integer status;
    private String isDelete;

}
