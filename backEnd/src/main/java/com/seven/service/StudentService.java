package com.seven.service;

import com.seven.domain.dto.StudentRegisterDTO;
import com.seven.domain.dto.StudentUserDTO;
import com.seven.domain.dto.UpdateStudentDTO;
import com.seven.domain.entity.Student;

public interface StudentService {
    Student registerStudent(StudentRegisterDTO studentRegisterDTO);

    Integer updateStudent(UpdateStudentDTO updateStudentDTO);

    Integer updateStudent(StudentUserDTO studentUserDTO);
}
