package com.seven.mapper;

import com.seven.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 查询用户通过username
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    public User selectUserByUsername(@Param("username")String username);

    /**
     * 插入用户通过username和password
     * @param username
     * @param password
     * @return
     */
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    public int insertUser(@Param("username") String username, @Param("password") String password);
}
