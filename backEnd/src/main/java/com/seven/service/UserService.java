package com.seven.service;

import com.seven.domain.vo.MessageModel;

public interface UserService {
    MessageModel selectUserByUsernameAndPassword(String username, String password);
    MessageModel insertUser(String username, String number, String password);
}
