package com.bookManage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookManage.domain.dto.SearchDTO;
import com.bookManage.domain.entity.Book;
import com.bookManage.domain.pojo.Result;
import com.bookManage.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 */

@Slf4j
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService ;

    /**
     * 分页查询书籍
     * @param searchDTO
     * @return
     */
    @PostMapping("/searchPage")
    public Result searchPage(@RequestBody SearchDTO searchDTO){
        log.info("searchDTO:{}",searchDTO);

        List<Book> list = bookService.searchPage(searchDTO);
        return Result.success(list);
    }

    /**
     * 通过类型获取所有该类型书籍
     * @param typeId
     * @return
     */
    @GetMapping("/{typeId}")
    public Result selectByTypeId(@PathVariable Integer typeId){
        log.info("typeId:{}",typeId);
        return Result.success(bookService.selectByTypeId(typeId));
    }

    /**
     * 获取下一页
     * @param currentPage
     * @return
     */
    @GetMapping("/nextPage/{currentPage}")
    public Result nextPage(@PathVariable Integer currentPage){
        log.info("page:{}",currentPage); 
        return Result.success(bookService.nextPage(currentPage));
    }

    /**
     * 获取图书信息
     * @param bookId
     * @return
     */
    @GetMapping("/getBook/{bookId}")
    public Result getBookTypeList(@PathVariable Integer bookId){

        log.info("bookId:{}",bookId);

        if(bookId == null){
            return Result.error("bookId不能为空");
        }

        Book book = bookService.getOne(new QueryWrapper<Book>().eq("book_id", bookId));
        return Result.success(book);
    }
}
