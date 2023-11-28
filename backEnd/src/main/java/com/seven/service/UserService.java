package com.seven.service;

import com.seven.domain.vo.MessageModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public MessageModel selectUserByUsernameAndPassword(String username, String password);
    public MessageModel insertUser(String username, String number, String password);
}
