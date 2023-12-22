package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.constant.MessageConstant;
import com.seven.constant.StatusConstant;
import com.seven.domain.dto.LoginDTO;
import com.seven.domain.dto.RegisterDTO;
import com.seven.domain.entity.Student;
import com.seven.domain.entity.User;
import com.seven.exception.AccountNotFoundException;
import com.seven.exception.BaseException;
import com.seven.exception.PasswordErrorException;
import com.seven.mapper.StudentMapper;
import com.seven.mapper.UserMapper;
import com.seven.service.UserService;
import com.seven.utils.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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
    private StudentMapper studentMapper;

    /**
     * 通过账号名和密码查询用户
     *
     * @param loginDTO
     * @return
     */
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //查询用户是否存在
        User user = userMapper.selectUserByUsername(username);
        //用户密码进行md5加密和数据库密码进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(password);
        if (user == null) {
            //用户不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        } else if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return user;
    }

    /**
     * 创建新user数据的同时建立一个对应的student数据
     *
     * @param registerDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer register(RegisterDTO registerDTO) {
        User user = new User();

        user = user.builder()
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                .phone(registerDTO.getPhone())
                .build();

        log.info("user:{}", user);
        //设置账号状态为启用
        user.setStatus(StatusConstant.ENABLE);
        //设置密码并进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        Integer studentId = studentMapper.selectIdByName(registerDTO.getStudentName());

        //学生查不到
        if (studentId == null) {
            log.info("学生需注册");
            return CodeUtil.FAILED;
        } else {
            log.info("学生已注册");
            user.setStudentId(studentId);
        }

        userMapper.insertUser(user);
        user = userMapper.selectOne(new QueryWrapper<User>().eq("username", registerDTO.getUsername()));
        log.info("userId:{}", user.getUserId());
        //返回学生表需要插入的userId
        return user.getUserId();
    }

    /**
     * 传入学号更新用户密码
     * @param studentNumber
     * @param password
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(String studentNumber, String password) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
                .eq(Student::getStudentNumber, studentNumber);

        Student student = studentMapper.selectOne(queryWrapper);
        if(student == null){
            throw new BaseException("学号不存在");
        }
        Integer userId = student.getUserId();

        if(userId == null){
            throw new BaseException("该学号未注册");
        }else{
            User user = userMapper.selectById(userId);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            userMapper.updateById(user);
        }
    }

    /**
     * 传入学号更新用户邮箱
     * @param studentNumber
     * @param email
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmail(String studentNumber, String email) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
               .eq(Student::getStudentNumber, studentNumber);

        Student student = studentMapper.selectOne(queryWrapper);
        if(student == null){
            throw new BaseException("学号不存在");
        }
        student.setStudentEmail(email);
        studentMapper.update(student, queryWrapper);
    }

    /**
     * 传入学号更新学生手机
     * @param studentNumber
     * @param studentPhone
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhone(String studentNumber, String studentPhone) {
        User user = new User();

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
               .eq(Student::getStudentNumber, studentNumber);

        Student student = studentMapper.selectOne(queryWrapper);
        if(student == null){
            throw new BaseException("学号不存在");
        }

        user.setPhone(studentPhone);
        userMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getStudentId, student.getStudentId()));
    }

    /**
     * 传入学号进行用户注销
     * @param studentNumber
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String studentNumber) {
        User user = new User();

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>()
                .eq(Student::getStudentNumber, studentNumber);

        Student student = studentMapper.selectOne(queryWrapper);
        if(student == null){
            throw new BaseException("学号不存在");
        }

        userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getStudentId, student.getStudentId()));
    }
}
