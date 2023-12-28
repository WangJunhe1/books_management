package com.bookManage.controller;


import com.bookManage.domain.pojo.Result;
import com.bookManage.domain.vo.BookTypeVO;
import com.bookManage.service.BookTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/bookType")
@CrossOrigin
@Slf4j
public class BookTypeController {
    @Autowired
    BookTypeService bookTypeService;

    /**
     * 获取图书类型列表
     * @return
     */
    @GetMapping("/getBookType")
    public Result getBookTypeList() {
        List<BookTypeVO> bookTypeList = bookTypeService.getBookTypeList();

        return Result.success(bookTypeList);
    }
}
