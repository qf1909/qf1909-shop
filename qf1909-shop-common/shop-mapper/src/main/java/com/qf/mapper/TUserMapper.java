package com.qf.mapper;

import com.qf.entity.TUser;


public interface TUserMapper {
    TUser selectByUsername(String username);

    int insert(TUser record);
}
