package com.qf.mapper.register;


import com.qf.entity.TUser;
import org.apache.ibatis.annotations.Param;

public interface RegisterMapper {

//    void  insertRegisterUser(@Param("password") String password,@Param("phone") String phone);
    void  insertRegisterUser(TUser user);
}
