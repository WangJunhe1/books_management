package com.seven.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.domain.dto.SearchDTO;
import com.seven.domain.entity.Book;
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
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService ;

    /**
     * 分页查询书籍
     * @param page
     * @param size
     * @param searchDTO
     * @return
     */
    @GetMapping("/searchPage")
    public Result searchPage(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             @RequestBody SearchDTO searchDTO){
        log.info("page:{},size:{},searchDTO:{}",page,size,searchDTO);

        PageBean pageBean = bookService.searchPage(page,size,searchDTO);
        return Result.success(pageBean);
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
