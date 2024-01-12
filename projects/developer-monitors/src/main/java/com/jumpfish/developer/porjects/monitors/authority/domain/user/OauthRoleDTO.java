package com.jumpfish.developer.porjects.monitors.authority.domain.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OauthRoleDTO implements Serializable {

    private static final long serialVersionUID = -2281358314400066262L;

    private String roleName;

    private String roleCode;

    private String systemVersion;

    private String systemCode;

    // private TreeNode<OauthMenuDTO> tree;

    private List<OauthMenuDTO> oauthMenus;
}
