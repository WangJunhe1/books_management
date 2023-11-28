package com.seven.service;

import com.seven.domain.dto.LoginDTO;
import com.seven.domain.entity.User;

public interface UserService {
     User login(LoginDTO loginDTO);
     Integer insertUser(String username, String number, String password) ;
}
