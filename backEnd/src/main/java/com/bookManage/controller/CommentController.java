package com.bookManage.controller;


import com.bookManage.constant.ImageFileConstant;
import com.bookManage.constant.JwtClaimsConstant;
import com.bookManage.domain.dto.BookIdCommentDTO;
import com.bookManage.domain.pojo.Result;
import com.bookManage.domain.vo.MyCommentVO;
import com.bookManage.domain.vo.SelectCommentVO;
import com.bookManage.properties.JwtProperties;
import com.bookManage.service.CommentService;
import com.bookManage.utils.JwtUtil;
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

    /**
     * 评论功能
     * @param
     * @return
     */
    @GetMapping("/getComment")
    public Result getCommentByUserId(ServletRequest request){

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        List<MyCommentVO> commentList = commentService.getCommentByUserId(userId);

        return Result.success(commentList);
    }

    /**
     * 通过图书id获取页面信息
     * @param bookId
     * @return
     */
    @GetMapping("/{bookId}")
    public Result getInfoByBookId(@PathVariable Integer bookId) {

        List<SelectCommentVO> selectCommentVOList = commentService.selectInfoByBookId(bookId);

        return Result.success(selectCommentVOList);
    }

    /**
     * 发布评论
     * @param bookIdCommentDTO
     * @param request
     * @return
     */
    @PostMapping("/postComment")
    public Result postComment(@RequestBody BookIdCommentDTO bookIdCommentDTO, ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        bookIdCommentDTO.setUserId(userId);
        System.out.println(bookIdCommentDTO);
        commentService.postComment(bookIdCommentDTO);

        return Result.success(ImageFileConstant.UPLOAD_SUCCESS);
    }
}
