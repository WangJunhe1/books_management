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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjh
 * @since 2023-12-22
 */
@Slf4j
@RestController
@RequestMapping("/borrow")
@CrossOrigin
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
    public Result borrowBook(@PathVariable Integer bookId, ServletRequest request){
        log.info("借书id:{}",bookId);
        BorrowDTO borrowDTO = new BorrowDTO();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try{
            String token = httpRequest.getHeader("token");
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            log.info("学生信息:{},bookId:{}",student,bookId);
            borrowDTO = borrowDTO.builder().bookId(bookId).borrowStudentNumber(student.getStudentNumber()).build();
            log.info("借书dto:{}",borrowDTO);

            if(borrowDTO.getBookId() == null || borrowDTO.getBorrowStudentNumber() == null) {
                throw new Exception("参数错误");
            }
            borrowService.borrowBook(borrowDTO);
        } catch (Exception e){
            return Result.error("借阅失败");
        }
        return Result.success("借书成功");
    }

    @GetMapping("/myBorrow")
    public Result myBorrow(ServletRequest request){

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

        Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

        Student student = studentService.getStudent(userId);

        if(student == null){
            return Result.error("获取借书信息失败");
        }

        return Result.success(borrowService.getMyBorrow(student.getStudentNumber()));
    }

    @PutMapping("/return")
    public Result returnBorrow(@RequestParam Integer bookId ,ServletRequest request){

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String token = httpRequest.getHeader("token");
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            Integer userId = (Integer) claims.get(JwtClaimsConstant.USER_ID);

            Student student = studentService.getStudent(userId);

            if(student == null){
                return Result.error("获取借书信息失败");
            }
            if(bookId == null){
                return Result.error("参数错误");
            }

            BorrowDTO borrowDTO = new BorrowDTO().builder()
                    .bookId(bookId)
                    .borrowStudentNumber(student.getStudentNumber())
                    .build();

            log.info("借书dto:{}",borrowDTO);;
            borrowService.returnBorrow(borrowDTO);
        }catch (Exception e){
            return Result.error("归还失败："+e.getMessage());
        }
        return Result.success("归还成功");
    }
}
