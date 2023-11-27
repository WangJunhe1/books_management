package com.seven.service;

import com.seven.dao.UserDao;
import com.seven.domain.User;
import com.seven.domain.vo.MessageModel;
import com.seven.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 通过账号名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public MessageModel selectUserByUsernameAndPassword(String username, String password) {
        MessageModel messageModel = new MessageModel();
        User user = userDao.selectUserByUsername(username);
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


    public MessageModel insertUser(String username, String password) {
        MessageModel messageModel = new MessageModel();
        User user = userDao.selectUserByUsername(username);     //  查询这个账号名有没有重复
        if (user != null) {     //  账号名重复
            messageModel.setCode(CodeUtils.FAILED_REGISTER_USERNAME);
            messageModel.setMessage("用户注册失败，账号名已存在");
            messageModel.setObject(null);
        } else {
            int result = userDao.insertUser(username, password);    //  插入数据
            if (result > 0) {
                messageModel.setCode(CodeUtils.SUCCESS);
                messageModel.setMessage("注册成功");
                messageModel.setObject(userDao.selectUserByUsername(username));
            }
        }
        return messageModel;
    }
}
