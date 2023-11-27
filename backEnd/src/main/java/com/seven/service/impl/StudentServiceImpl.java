package com.seven.service.impl;

import com.seven.mapper.StudentMapper;
import com.seven.domain.entity.Student;
import com.seven.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 返回创建的新student数据
     * @param student_name
     * @param student_number
     * @return
     */
    public Student insertStudent(String student_name, String student_number) {
        int result = studentMapper.insertStudent(student_name, student_number);
        Student student = null;
        if (result > 0) {
             student = studentMapper.selectByStudentName(student_name);
        }
        return student;
    }
}
