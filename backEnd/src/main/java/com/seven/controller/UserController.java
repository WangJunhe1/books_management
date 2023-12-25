package com.seven.controller;

import com.seven.constant.JwtClaimsConstant;
import com.seven.constant.PasswordConstant;
import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.dto.StudentUserDTO;
import com.seven.domain.entity.User;
import com.seven.domain.pojo.Result;
import com.seven.domain.vo.UserInfoVO;
import com.seven.properties.JwtProperties;
import com.seven.service.StudentService;
import com.seven.service.UserService;
import com.seven.utils.CodeUtil;
import com.seven.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StudentService studentService;

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {

        log.info("用户登录：{}", loginDTO);

        User user = userService.login(loginDTO);

        user.setPassword(PasswordConstant.SECURE_PASSWORD);

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
    public Result register(@RequestBody RegisterDTO registerDTO) {
        log.info("新增员工：{}", registerDTO);
        Integer code = userService.register(registerDTO);
        if(code.equals(CodeUtil.FAILED)){
            return Result.error();
        }

        StudentUserDTO studentUserDTO = new StudentUserDTO()
                .builder()
                .userId(code)
                .studentNumber(registerDTO.getStudentNumber())
                .build();

        code = studentService.updateStudent(studentUserDTO);

        if(code.equals(CodeUtil.FAILED)){
            return Result.error("学生需注册");
        }

        return Result.success("注册成功");
    }

    /**
     * 更新密码无验证
     * @param studentNumber
     * @param password
     * @return
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestParam(required = true) String studentNumber,
                                 @RequestParam(required = true) String password){
        System.out.println("password:"+password);
        userService.updatePassword(studentNumber, password);
        return Result.success();
    }

    /**
     * 更新邮箱无验证
     * @param studentNumber
     * @param studentEmail
     * @return
     */
    @PutMapping("/updateEmail")
    public Result updateEmail(@RequestParam(required = true) String studentNumber,
                             @RequestParam(required = true) String studentEmail){
        userService.updateEmail(studentNumber, studentEmail);
        return Result.success();
    }

    /**
     * 更新手机号无验证
     * @param studentNumber
     * @param studentPhone
     * @return
     */
    @PutMapping("/updatePhone")
    public Result updatePhone(@RequestParam(required = true) String studentNumber,
                              @RequestParam(required = true) String studentPhone){
        userService.updatePhone(studentNumber, studentPhone);
        return Result.success();
    }

    /**
     * 注销账号
     * @param studentNumber
     * @return
     */
    @DeleteMapping("/delete/{studentNumber}")
    public Result delete(@PathVariable String studentNumber){
        userService.delete(studentNumber);
        return Result.success();
    }
}
