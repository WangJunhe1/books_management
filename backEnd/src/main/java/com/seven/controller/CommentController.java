package com.seven.controller;


import com.seven.constant.JwtClaimsConstant;
import com.seven.domain.dto.BookIdCommentDTO;
import com.seven.domain.dto.BorrowDTO;
import com.seven.domain.entity.Comment;
import com.seven.domain.entity.Student;
import com.seven.domain.pojo.Result;
import com.seven.domain.vo.MyCommentVO;
import com.seven.domain.vo.SelectCommentVO;
import com.seven.properties.JwtProperties;
import com.seven.service.BorrowService;
import com.seven.service.CommentService;
import com.seven.service.StudentService;
import com.seven.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StudentService studentService;

    /**
     * 通过user_id查找评论
     * @param
     * @return
     */
    @GetMapping("/getComment")
    public Result getCommentByUserId(ServletRequest request){

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        List<MyCommentVO> commentList = commentService.getCommentByUserId(userId);

        return Result.success(commentList);
    }

    /**
     * 通过图书id查找评论
     * @param bookId
     * @return
     */
    @GetMapping("/{bookId}")
    public Result getInfoByBookId(@PathVariable Integer bookId) {

        List<SelectCommentVO> selectCommentVOList = commentService.selectInfoByBookId(bookId);

        return Result.success(selectCommentVOList);
    }

    @PostMapping("/postComment")
    public Result postComment(@RequestBody BookIdCommentDTO bookIdCommentDTO, ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        bookIdCommentDTO.setUserId(userId);
        commentService.postComment(bookIdCommentDTO);

        return Result.success();
    }
}
