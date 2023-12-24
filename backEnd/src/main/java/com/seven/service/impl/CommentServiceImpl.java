package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seven.domain.dto.BookIdCommentDTO;
import com.seven.domain.entity.Book;
import com.seven.domain.entity.Comment;
import com.seven.domain.vo.MyCommentVO;
import com.seven.domain.vo.SelectCommentVO;
import com.seven.mapper.BookMapper;
import com.seven.mapper.CommentMapper;
import com.seven.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<MyCommentVO> getCommentByUserId(int userId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<Comment>();
        lqw.eq(Comment::getUserId, userId);

        List<Comment> list = commentMapper.selectList(lqw);


        List<MyCommentVO> myCommentlist = new ArrayList<>();

        for (Comment comment : list) {

            LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<Book>()
                    .eq(Book::getBookId, comment.getBookId());

            Book book = bookMapper.selectOne(queryWrapper);

            MyCommentVO myCommentVO = new MyCommentVO();
            myCommentVO.setCommentContent(comment.getCommentContent());
            myCommentVO.setCommentTime(comment.getCommentTime());
            myCommentVO.setCommentStatus(comment.getCommentStatus());
            myCommentVO.setBookId(comment.getBookId());
            myCommentVO.setBookName(book.getBookName());

            myCommentlist.add(myCommentVO);
        }

        return myCommentlist;
    }

    @Override
    public List<SelectCommentVO> selectInfoByBookId(int bookId) {
        List<SelectCommentVO> result = commentMapper.selectCommentsWithUserInfo(bookId);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
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
