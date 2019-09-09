package com.sdwfqin.spring_boot_demo.mapper;

import com.sdwfqin.spring_boot_demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM test_tb_user")
    List<User> getAll();

    /**
     * 使用#符号和$符号的不同
     * # 自带''
     * $ 不带''
     */
    @Select("SELECT * FROM user WHERE `state` = #{state}")
    List<User> getAllPage(@Param("state") int state);

    @Select("SELECT * FROM user WHERE `id` = #{uid}")
    User getUser(@Param("uid") String uid);
}
