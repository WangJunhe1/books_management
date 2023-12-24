package com.seven.controller;

import com.seven.constant.JwtClaimsConstant;
import com.seven.domain.dto.StudentRegisterDTO;
import com.seven.domain.dto.UpdateStudentDTO;
import com.seven.domain.entity.Student;
import com.seven.domain.pojo.Result;
import com.seven.properties.JwtProperties;
import com.seven.service.StudentService;
import com.seven.utils.CodeUtil;
import com.seven.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student")
@CrossOrigin
@Slf4j
public class StudentController {
    @Autowired
    JwtProperties jwtProperties ;
    @Autowired
    private StudentService studentService;

    /**
     * 学生注册页面
     * @param studentRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public Result registerStudent(@RequestBody StudentRegisterDTO studentRegisterDTO) {
        log.info("registerStudent: {}", studentRegisterDTO);

        Student student = studentService.registerStudent(studentRegisterDTO);

        return Result.success(student);
    }

    /**
     * 更新学生信息操作
     * @param updateStudentDTO
     * @return
     */
    @PutMapping("/update")
    public Result updateStudent(@RequestBody UpdateStudentDTO updateStudentDTO) {
        log.info("updateStudent: {}", updateStudentDTO);

        Integer code = studentService.updateStudent(updateStudentDTO);

        if(code.equals(CodeUtil.FAILED)){
            return Result.error("更新失败");
        }

        return Result.success();
    }

    /**
     * 获取学生信息
     * @param request
     * @return
     */
    @GetMapping("/getStudent")
    public Result getStudent(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        Student student = studentService.getStudent(userId);

        return Result.success(student);
    }
}
