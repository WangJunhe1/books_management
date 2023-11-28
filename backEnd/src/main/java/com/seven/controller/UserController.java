package com.seven.controller;


import com.seven.constant.JwtClaimsConstant;
import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.entity.User;
import com.seven.domain.pojo.Result;
import com.seven.service.impl.UserServiceImpl;
import com.seven.utils.CodeUtil;
import com.seven.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(tags = "用户接口")
public class UserController {
    @ApiModelProperty(value = "用户服务接口")
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {

        log.info("用户登录：{}", loginDTO);

        User user = userService.login(loginDTO);

        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());

        if(user == null)
            return Result.error();
        return Result.success(user);
    }


    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public Result register(@RequestBody RegisterDTO registerDTO) {
        Integer code = userService.register(registerDTO);
        if(code.equals(CodeUtil.SUCCESS))
            return Result.success();
        else
            return Result.error();
    }
}
