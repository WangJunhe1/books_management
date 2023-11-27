package com.seven.service;

import com.seven.dao.StudentDao;
import com.seven.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * 返回创建的新student数据
     * @param student_name
     * @param student_number
     * @return
     */
    public Student insertStudent(String student_name, String student_number) {
        int result = studentDao.insertStudent(student_name, student_number);
        Student student = null;
        if (result > 0) {
             student = studentDao.selectByStudentName(student_name);
        }
        return student;
    }

}
