package com.bookManage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookManage.domain.entity.Comment;
import com.bookManage.domain.vo.SelectCommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<SelectCommentVO> selectCommentsWithUserInfo(int bookId);
}
