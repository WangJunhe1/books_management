package com.seven.controller;

import com.seven.domain.dto.StudentRegisterDTO;
import com.seven.domain.dto.UpdateStudentDTO;
import com.seven.domain.entity.Student;
import com.seven.domain.pojo.Result;
import com.seven.service.StudentService;
import com.seven.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/register")
    public Result registerStudent(@RequestBody StudentRegisterDTO studentRegisterDTO) {
        log.info("registerStudent: {}", studentRegisterDTO);

        Student student = studentService.registerStudent(studentRegisterDTO);

        return Result.success(student);
    }

    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public Result updateStudent(@RequestBody UpdateStudentDTO updateStudentDTO) {
        log.info("updateStudent: {}", updateStudentDTO);

        Integer code = studentService.updateStudent(updateStudentDTO);

        if(code.equals(CodeUtil.FAILED)){
            return Result.error("更新失败");
        }

        return Result.success();
    }
}
