package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getCommentByUserId2(@Param("user_id")int User_Id);
}
