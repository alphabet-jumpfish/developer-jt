package com.jumpfish.developer.porjects.monitors.authority.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class OauthUserDTO extends User implements Serializable {

    public OauthUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    private String loginName;

    private Long userId;

    private String domain;

    private String labelCode;

    private List<OauthRoleDTO> oauthRoleDTOS;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public List<OauthRoleDTO> getOauthRoleDTOS() {
        return oauthRoleDTOS;
    }

    public void setOauthRoleDTOS(List<OauthRoleDTO> oauthRoleDTOS) {
        this.oauthRoleDTOS = oauthRoleDTOS;
    }
}
