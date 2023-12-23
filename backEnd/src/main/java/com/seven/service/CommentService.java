package com.seven.service;

import com.seven.domain.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByUserId(int UserId);
}
