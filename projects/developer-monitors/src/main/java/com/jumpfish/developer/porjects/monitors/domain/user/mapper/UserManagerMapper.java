package com.jumpfish.developer.porjects.monitors.domain.user.mapper;

import com.jumpfish.developer.porjects.monitors.domain.user.entity.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserManagerMapper {

    @Select("SELECT id FROM project_user")
    public List<UserPO> selectUsers();
}
