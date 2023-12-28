package com.bookManage.service;

import com.bookManage.domain.dto.StudentRegisterDTO;
import com.bookManage.domain.dto.StudentUserDTO;
import com.bookManage.domain.dto.UpdateStudentDTO;
import com.bookManage.domain.entity.Student;

public interface StudentService {
    Student registerStudent(StudentRegisterDTO studentRegisterDTO);

    Integer updateStudent(UpdateStudentDTO updateStudentDTO);

    Integer updateStudent(StudentUserDTO studentUserDTO);

    void uploadImage(String studentNumber, String filePath);

    Student getStudent(Integer userId);
}
