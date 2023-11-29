package com.seven.service.impl;

import com.seven.constant.MessageConstant;
import com.seven.constant.StatusConstant;
import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.entity.User;
import com.seven.exception.AccountNotFoundException;
import com.seven.exception.PasswordErrorException;
import com.seven.mapper.UserMapper;
import com.seven.service.StudentService;
import com.seven.service.UserService;
import com.seven.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * @author :Wjh
 * @since :2023/11/28 10:05
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentService studentService;

    /**
     * 通过账号名和密码查询用户
     * @param loginDTO
     * @return
     */
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = userMapper.selectUserByUsername(username);

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
        if(user == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }else if(!password.equals(user.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return user;
    }

    /**
     * 创建新user数据的同时建立一个对应的student数据
     * @param registerDTO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer register(RegisterDTO registerDTO) {
        User user = new User();

        BeanUtils.copyProperties(registerDTO,user);
        //设置账号状态为启用
        user.setStatus(StatusConstant.ENABLE);
        //设置密码并进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        log.info("user:{}",user);

        userMapper.insertUser(user);
       return CodeUtil.SUCCESS;
    }
}
