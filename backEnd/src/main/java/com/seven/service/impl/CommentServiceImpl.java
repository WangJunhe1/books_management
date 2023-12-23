package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seven.domain.entity.Comment;
import com.seven.mapper.CommentMapper;
import com.seven.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void getCommentByUserId(int UserId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();
        lqw.eq(Comment::getUserId, UserId);
        Comment s = commentMapper.selectOne(lqw);
        System.out.println(s);
    }

    @Override
    public void getCommentByUserId2(int UserId) {
        List<Comment> customers = commentMapper.getCommentByUserId2(UserId);

        System.out.println(customers);
    }
}
