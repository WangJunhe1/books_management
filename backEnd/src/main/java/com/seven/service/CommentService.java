package com.seven.service;

import com.seven.domain.dto.BookIdCommentDTO;
import com.seven.domain.entity.Comment;
import com.seven.domain.vo.MyCommentVO;
import com.seven.domain.vo.SelectCommentVO;

import java.util.List;

public interface CommentService {
    List<MyCommentVO> getCommentByUserId(int UserId);

    List<SelectCommentVO> selectInfoByBookId(int BookId);

    void postComment(BookIdCommentDTO bookIdCommentDTO);
}
