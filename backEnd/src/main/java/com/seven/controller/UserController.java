package com.seven.controller;


import com.seven.domain.dto.LoginDTO;
import com.seven.domain.entity.User;
import com.seven.domain.pojo.Result;
import com.seven.service.impl.UserServiceImpl;
import com.seven.util.CodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户接口")
public class UserController {
    @ApiModelProperty(value = "用户服务接口")
    @Autowired
    private UserServiceImpl service;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody LoginDTO loginDTO) {
        User user = service.login(loginDTO);
        if(user == null)
            return Result.error();
        return Result.success(user);
    }


    /**
     * 用户注册
     * @param username
     * @param number
     * @param password
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(String username, String number, String password) {
        Integer code = service.insertUser(username, number, password);
        if(code.equals(CodeUtils.SUCCESS))
            return Result.success();
        else
            return Result.error();
    }
}
