package com.seven.controller;


import com.seven.constant.JwtClaimsConstant;
import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.entity.User;
import com.seven.domain.pojo.Result;
import com.seven.domain.vo.UserInfoVO;
import com.seven.properties.JwtProperties;
import com.seven.service.UserService;
import com.seven.utils.CodeUtil;
import com.seven.utils.JwtUtil;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {

        log.info("用户登录：{}", loginDTO);

        User user = userService.login(loginDTO);

        user.setPassword("********");

        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserInfoVO userInfo = new UserInfoVO().builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .phone(user.getPhone())
                .token(token).build();

        log.info("token：{}", token);
        if(user == null)
            return Result.error();
        return Result.success(userInfo);
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }


    /**
     * 用户注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public Result register(@RequestBody RegisterDTO registerDTO) {
        log.info("新增员工：{}", registerDTO);
        Integer code = userService.register(registerDTO);
        if(code.equals(CodeUtil.SUCCESS))
            return Result.success("登录成功");
        else
            return Result.error();
    }
}
