package com.bookManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bookManage.domain.dto.BookIdCommentDTO;
import com.bookManage.domain.entity.Book;
import com.bookManage.domain.entity.Comment;
import com.bookManage.domain.vo.MyCommentVO;
import com.bookManage.domain.vo.SelectCommentVO;
import com.bookManage.mapper.BookMapper;
import com.bookManage.mapper.CommentMapper;
import com.bookManage.service.CommentService;
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

            if(comment.getBookId() == null)
                continue;

            LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<Book>()
                    .eq(Book::getBookId, comment.getBookId());

            Book book = bookMapper.selectOne(queryWrapper);

            if(book == null)
                continue;

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
