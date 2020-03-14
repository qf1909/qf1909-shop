package com.qf.mapper.user;

import com.qf.entity.TUser;
import org.apache.ibatis.annotations.Param;


public interface TUserMapper {
    TUser selectByUsername(@Param("username") String username);
}
