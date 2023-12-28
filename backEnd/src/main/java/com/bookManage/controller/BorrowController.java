package com.bookManage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bookManage.constant.BorrowConstant;
import com.bookManage.constant.JwtClaimsConstant;
import com.bookManage.constant.MessageConstant;
import com.bookManage.domain.dto.BorrowDTO;
import com.bookManage.domain.entity.Borrow;
import com.bookManage.domain.entity.Student;
import com.bookManage.domain.pojo.Result;
import com.bookManage.properties.JwtProperties;
import com.bookManage.service.BorrowService;
import com.bookManage.service.StudentService;
import com.bookManage.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private StudentService studentService;

    /**
     * 借书
     * @param bookId
     * @return
     */
    @PostMapping("/{bookId}")
    public Result borrowBook(@PathVariable Integer bookId, ServletRequest request) {
        log.info("借书id:{}", bookId);
        BorrowDTO borrowDTO = new BorrowDTO();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            log.info("学生信息:{},bookId:{}", student, bookId);
            borrowDTO = borrowDTO.builder().bookId(bookId).borrowStudentNumber(student.getStudentNumber()).build();
            log.info("借书dto:{}", borrowDTO);

            if (borrowDTO.getBookId() == null || borrowDTO.getBorrowStudentNumber() == null) {
                throw new Exception(MessageConstant.PARAM_ERROR);
            }
            borrowService.borrowBook(borrowDTO);
        } catch (Exception e) {
            return Result.error(BorrowConstant.BORROW_FAILED+e.getMessage());
        }
        return Result.success(BorrowConstant.BORROW_SUCCESS);
    }

    /**
     * 获取我的借阅
     * @param request
     * @return
     */
    @GetMapping("/myBorrow")
    public Result myBorrow(ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        Student student = studentService.getStudent(userId);

        if (student == null) {
            return Result.error(BorrowConstant.GET_BORROW_FAILED);
        }

        return Result.success(borrowService.getMyBorrow(student.getStudentNumber()));
    }


    /**
     * 图书归还
     * @param bookId
     * @param request
     * @return
     */
    @PutMapping("/return")
    public Result returnBorrow(@RequestParam Integer bookId, ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            if (student == null) {
                return Result.error(BorrowConstant.GET_BORROW_FAILED);
            }
            if (bookId == null) {
                return Result.error(MessageConstant.PARAM_ERROR);
            }

            BorrowDTO borrowDTO = new BorrowDTO().builder()
                    .bookId(bookId)
                    .borrowStudentNumber(student.getStudentNumber())
                    .build();

            log.info("借书dto:{}", borrowDTO);

            borrowService.returnBorrow(borrowDTO);
        } catch (Exception e) {
            return Result.error(BorrowConstant.RETURN_FAILED + e.getMessage());
        }
        return Result.success(BorrowConstant.RETURN_SUCCESS);
    }


    /**
     * 获取借阅状态
     * @param bookId
     * @param request
     * @return
     */
    @GetMapping("/{bookId}")
    public Result getBorrow(@PathVariable Integer bookId, ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Borrow borrow = new Borrow();
        Integer status = 0;

        try {
            String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            if (student == null) {
                return Result.error(BorrowConstant.GET_BORROW_FAILED);
            }
            if (bookId == null) {
                return Result.error(MessageConstant.PARAM_ERROR);
            }

            borrow = borrowService.getOne(new LambdaQueryWrapper<Borrow>()
                    .eq(Borrow::getBookId, bookId)
                    .eq(Borrow::getBorrowStudentNumber, student.getStudentNumber()));

            if (borrow != null) {
                status = 1;
            }
        } catch (Exception e) {
            return Result.error(BorrowConstant.GET_BORROW_FAILED + e.getMessage());
        }
        return Result.success(status);
    }

    /**
     * 续订功能
     * @param bookId
     * @param request
     * @return
     */
    @PutMapping("/{bookId}")
    public Result renewalBorrow(@PathVariable Integer bookId, ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String token = httpRequest.getHeader(JwtClaimsConstant.TOKEN);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            if (student == null) {
                return Result.error(BorrowConstant.GET_BORROW_FAILED);
            }
            if (bookId == null) {
                return Result.error(MessageConstant.PARAM_ERROR);
            }

            BorrowDTO borrowDTO = new BorrowDTO().builder()
                    .bookId(bookId)
                    .borrowStudentNumber(student.getStudentNumber())
                    .build();

            log.info("借书dto:{}", borrowDTO);
            borrowService.renewalBorrow(borrowDTO);
        } catch (Exception e) {
            return Result.error(BorrowConstant.RENEWAL_FAILED + e.getMessage());
        }
        return Result.success(BorrowConstant.RENEWAL_SUCCESS);
    }
}
