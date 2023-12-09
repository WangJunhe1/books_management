package com.seven.controller;


import com.seven.domain.dto.SearchDTO;
import com.seven.domain.pojo.PageBean;
import com.seven.domain.pojo.Result;
import com.seven.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author wjh
 * @since 2023-12-04
 */

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService ;

    @GetMapping("/searchPage")
    public Result searchPage(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             @RequestBody SearchDTO searchDTO){
        log.info("page:{},size:{},searchDTO:{}",page,size,searchDTO);

        PageBean pageBean = bookService.searchPage(page,size,searchDTO);
        return Result.success(pageBean);
    }

    @GetMapping("/{typeId}")
    public Result selectByTypeId(@PathVariable Integer typeId){
        log.info("typeId:{}",typeId);
        return Result.success(bookService.selectByTypeId(typeId));
    }
}
