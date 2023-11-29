package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户通过username
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User selectUserByUsername(@Param("username") String username);

    /**
     * 插入用户通过username和password
     *
     * @param user
     * @return
     */
    @Insert("insert into user (username, password, phone, status,create_time,update_time, student_id) " +
            "value (#{user.username},#{user.password},#{user.phone},#{user.status}," +
            "#{user.createTime},#{user.updateTime},#{user.studentId})")
    int insertUser(@Param("user") User user);
}
