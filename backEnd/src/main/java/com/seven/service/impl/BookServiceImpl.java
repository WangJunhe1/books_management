package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.seven.domain.dto.SearchDTO;
import com.seven.domain.entity.Book;
import com.seven.domain.pojo.PageBean;
import com.seven.mapper.BookMapper;
import com.seven.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author wjh
 * @since 2023-12-04
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> searchPage(SearchDTO searchDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(searchDTO,book);

        List<Book> bookList = bookMapper.searchList(book);
        return bookList;
    }

    @Override
    public List<Book> selectByTypeId(Integer typeId) {
        List<Book> lists = bookMapper.selectList(new QueryWrapper<Book>().eq("book_typeId",typeId));
        return lists;
    }

    @Override
    public PageBean nextPage(Integer currentPage) {
        PageHelper.startPage(currentPage, 4);
        Book book = new Book();

        List<Book> bookList = bookMapper.selectList(new LambdaQueryWrapper<>());
        Page<Book> pages = (Page<Book>) bookList;

        PageBean pageBean = new PageBean(pages.getTotal(),pages.getResult());
        return pageBean;
    }
}
