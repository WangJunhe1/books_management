package com.bookManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bookManage.domain.dto.SearchDTO;
import com.bookManage.domain.entity.Book;
import com.bookManage.domain.pojo.PageBean;
import com.bookManage.mapper.BookMapper;
import com.bookManage.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> searchPage(SearchDTO searchDTO) {
        String key = searchDTO.getSearchKey();

        List<Book> bookList = bookMapper.searchList(key);
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
