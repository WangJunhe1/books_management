package com.seven.controller;


import com.seven.domain.vo.MessageModel;
import com.seven.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl service;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/getUser")
    public MessageModel login(String username, String password) {
        return service.selectUserByUsernameAndPassword(username, password);
    }


    /**
     * 用户注册
     * @param username
     * @param number
     * @param password
     * @return
     */
    @PostMapping("/register")
    public MessageModel register(String username, String number, String password) {
        return service.insertUser(username, number, password);
    }

}
