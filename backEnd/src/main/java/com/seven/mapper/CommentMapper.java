package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.entity.Comment;
import com.seven.domain.vo.SelectCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    List<SelectCommentVO> selectCommentsWithUserInfo(int bookId);
}
