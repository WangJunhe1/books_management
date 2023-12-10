package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.annotation.AutoFill;
import com.seven.domain.entity.User;
import com.seven.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @AutoFill(value = OperationType.INSERT)
    int insertUser(@Param("user") User user);
}
