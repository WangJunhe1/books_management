package com.seven.service;

import com.seven.domain.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    public Student insertStudent(String student_name, String student_number);

}
