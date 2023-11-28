package com.seven.service.impl;

import com.seven.domain.entity.Student;
import com.seven.mapper.StudentMapper;
import com.seven.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author :Wjh
 * @since :2023/11/28 10:03
 */

@Service
public class StudentServiceImpl implements StudentService {
    private StudentMapper studentDao;
    public Student insertStudent(String student_name, String student_number) {
        int result = studentDao.insertStudent(student_name, student_number);
        Student student = null;
        if (result > 0) {
            student = studentDao.selectByStudentName(student_name);
        }
        return student;
    }
}
