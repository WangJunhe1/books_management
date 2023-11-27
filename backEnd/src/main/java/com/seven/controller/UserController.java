package com.seven.controller;


import com.seven.domain.User;
import com.seven.domain.vo.MessageModel;
import com.seven.service.UserService;
import com.seven.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/getUser")
    @ResponseBody
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
    @ResponseBody
    public MessageModel register(String username, String number, String password) {
        return service.insertUser(username, number, password);
    }

}
