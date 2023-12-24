package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seven.domain.dto.BookIdCommentDTO;
import com.seven.domain.entity.Comment;
import com.seven.domain.vo.SelectCommentVO;
import com.seven.mapper.BookIdCommentDTOMapper;
import com.seven.mapper.CommentMapper;
import com.seven.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BookIdCommentDTOMapper bookIdCommentDTOMapper;
    @Override
    public List<Comment> getCommentByUserId(int userId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();
        lqw.eq(Comment::getUserId, userId);

        List<Comment> s = commentMapper.selectList(lqw);

        return s;
    }

    @Override
    public List<SelectCommentVO> selectInfoByBookId(int bookId) {
        List<SelectCommentVO> result = commentMapper.selectCommentsWithUserInfo(bookId);

//        for (SelectCommentVO x : result) {
//            System.out.println(x);
//        }
        return result;
    }

    @Override
    public void postComment(BookIdCommentDTO bookIdCommentDTO) {
        Comment comment = new Comment();

        comment.setCommentContent(bookIdCommentDTO.getCommentContent());
        comment.setUserId(bookIdCommentDTO.getUserId());
        comment.setBookId(bookIdCommentDTO.getBookId());
        comment.setCommentTime(LocalDateTime.now());
        comment.setCommentStatus(bookIdCommentDTO.getCommentStatus());

        commentMapper.insert(comment);
    }
}
