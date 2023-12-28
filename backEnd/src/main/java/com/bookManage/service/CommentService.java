package com.bookManage.service;

import com.bookManage.domain.dto.BookIdCommentDTO;
import com.bookManage.domain.vo.MyCommentVO;
import com.bookManage.domain.vo.SelectCommentVO;

import java.util.List;

public interface CommentService {
    List<MyCommentVO> getCommentByUserId(int UserId);

    List<SelectCommentVO> selectInfoByBookId(int BookId);

    void postComment(BookIdCommentDTO bookIdCommentDTO);
}
