package com.seven.service;

import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.entity.User;

public interface UserService {
     User login(LoginDTO loginDTO);
     Integer register(RegisterDTO registerDTO) ;
}
