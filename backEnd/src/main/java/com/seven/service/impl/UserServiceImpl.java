package com.seven.service.impl;

import com.seven.mapper.UserMapper;
import com.seven.domain.entity.User;
import com.seven.domain.vo.MessageModel;
import com.seven.service.StudentService;
import com.seven.service.UserService;
import com.seven.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentService service;

    /**
     * 通过账号名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public MessageModel selectUserByUsernameAndPassword(String username, String password) {
        MessageModel messageModel = new MessageModel();
        User user = userMapper.selectUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                messageModel.setCode(CodeUtils.SUCCESS);
                messageModel.setMessage("用户登录成功");
                messageModel.setObject(user);
            } else {
                messageModel.setCode(CodeUtils.FAILED_PASSWORD);
                messageModel.setMessage("用户密码输入错误");
                messageModel.setObject(null);
            }

        } else {
            messageModel.setCode(CodeUtils.FAILED);
            messageModel.setMessage("用户登录失败");
            messageModel.setObject(null);
        }
        return messageModel;
    }

    /**
     * 创建新user数据的同时建立一个对应的student数据
     * @param username
     * @param number
     * @param password
     * @return
     */
    public MessageModel insertUser(String username, String number, String password) {
        MessageModel messageModel = new MessageModel();
        User user = userMapper.selectUserByUsername(username);     //  查询这个账号名有没有重复
        if (user != null) {     //  账号名重复
            messageModel.setCode(CodeUtils.FAILED_REGISTER_USERNAME);
            messageModel.setMessage("用户注册失败，账号名已存在");
            messageModel.setObject(null);
        } else {
            int result = userMapper.insertUser(username, password);    //  插入数据
            if (result > 0) {
                messageModel.setCode(CodeUtils.SUCCESS);
                messageModel.setMessage("注册成功");
                messageModel.setObject(userMapper.selectUserByUsername(username));
                service.insertStudent(username, number);
                // TODO: user表和student表对应的外键同步没有解决
            }
        }
        return messageModel;
    }
}
