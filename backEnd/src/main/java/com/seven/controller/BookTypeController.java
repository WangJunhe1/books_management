package com.seven.controller;


import com.seven.domain.pojo.Result;
import com.seven.domain.vo.BookTypeVO;
import com.seven.service.BookTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjh
 * @since 2023-12-02
 */
@RestController
@RequestMapping("/bookType")
@Slf4j
public class BookTypeController {
    @Autowired
    BookTypeService bookTypeService;

    @GetMapping("/getBookType")
    public Result getBookTypeList() {
        List<BookTypeVO> bookTypeList = bookTypeService.getBookTypeList();

        return Result.success(bookTypeList);
    }
}
