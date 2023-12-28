package com.bookManage.service;

import com.bookManage.domain.dto.LoginDTO;
import com.bookManage.domain.dto.RegisterDTO;
import com.bookManage.domain.entity.User;

public interface UserService {
     User login(LoginDTO loginDTO);
     Integer register(RegisterDTO registerDTO) ;
     void updatePassword(String studentNumber, String password);
     void updateEmail(String studentNumber, String email);
     void updatePhone(String studentNumber, String studentPhone);
     void delete(String studentNumber);
}
