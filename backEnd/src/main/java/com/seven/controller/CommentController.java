package com.seven.controller;


import com.seven.constant.JwtClaimsConstant;
import com.seven.domain.dto.BorrowDTO;
import com.seven.domain.entity.Student;
import com.seven.domain.pojo.Result;
import com.seven.properties.JwtProperties;
import com.seven.service.BorrowService;
import com.seven.service.StudentService;
import com.seven.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * 评论功能
     * @param
     * @return
     */
    @PostMapping("/{bookId}")
    public Result getCommentByUserId(Integer userId){

        return Result.success("");
    }
}
