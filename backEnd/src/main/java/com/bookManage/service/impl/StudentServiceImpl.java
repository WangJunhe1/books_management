package com.bookManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bookManage.domain.dto.StudentRegisterDTO;
import com.bookManage.domain.dto.StudentUserDTO;
import com.bookManage.domain.dto.UpdateStudentDTO;
import com.bookManage.domain.entity.Student;
import com.bookManage.mapper.StudentMapper;
import com.bookManage.service.StudentService;
import com.bookManage.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 用于学生注册
     * @param studentRegisterDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Student registerStudent(StudentRegisterDTO studentRegisterDTO) {
        Student student = new Student();
        log.info("studentRegisterDTO: {}", studentRegisterDTO);
        BeanUtils.copyProperties(studentRegisterDTO, student);

        Date date = studentRegisterDTO.getBirthday();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        student.setBirthday(localDateTime);

        log.info("student: {}", student);
        studentMapper.insert(student);
        return student;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateStudent(UpdateStudentDTO updateStudentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(updateStudentDTO, student);

        Date date = updateStudentDTO.getBirthday();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        student.setBirthday(localDateTime);

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<Student>()
                .eq("student_number", student.getStudentNumber());

        log.info("student: {}", student);
        Integer code = studentMapper.update(student, updateWrapper);

        if(code.equals(0)){
            return CodeUtil.FAILED;
        }
        return CodeUtil.SUCCESS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateStudent(StudentUserDTO studentUserDTO) {
        Student student = new Student();

        BeanUtils.copyProperties(studentUserDTO, student);

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<Student>()
                .eq("student_number", student.getStudentNumber());

        log.info("student: {}", student);
        Integer code = studentMapper.update(student, updateWrapper);

        if(code.equals(0)){
            return CodeUtil.FAILED;
        }
        return CodeUtil.SUCCESS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void uploadImage(String studentNumber, String filePath) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
                .eq(Student::getStudentNumber, studentNumber);

        Student student = new Student();
        student.setStudentNumber(studentNumber);
        student.setPortrait(filePath);

        studentMapper.update(student, queryWrapper);
    }

    @Override
    public Student getStudent(Integer userId) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
               .eq(Student::getUserId, userId);

        Student student = studentMapper.selectOne(queryWrapper);

        return student;
    }
}
