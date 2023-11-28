package com.seven.service.impl;

import com.seven.domain.dto.LoginDTO;
import com.seven.domain.entity.User;
import com.seven.mapper.UserMapper;
import com.seven.service.StudentService;
import com.seven.service.UserService;
import com.seven.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :Wjh
 * @since :2023/11/28 10:05
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Autowired
    private StudentService service;

    /**
     * 通过账号名和密码查询用户
     * @param loginDTO
     * @return
     */
    public User login(LoginDTO loginDTO) {
        User user = userDao.selectUserByUsername(loginDTO.getUsername());
        if (user != null) {
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 创建新user数据的同时建立一个对应的student数据
     * @param username
     * @param number
     * @param password
     * @return
     */
    public Integer insertUser(String username, String number, String password) {
        User user = userDao.selectUserByUsername(username);     //  查询这个账号名有没有重复
        if (user != null) {     //  账号名重复
            return CodeUtils.FAILED_REGISTER_USERNAME;
        } else {
            int result = userDao.insertUser(username, password);    //  插入数据
            if (result > 0) {
                service.insertStudent(username, number);
                return CodeUtils.SUCCESS;
                // TODO: user表和student表对应的外键同步没有解决
            }
        }
        return CodeUtils.FAILED;
    }
}
