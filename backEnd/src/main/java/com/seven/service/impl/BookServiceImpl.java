package com.seven.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
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
    public PageBean searchPage(Integer page, Integer size, SearchDTO searchDTO) {
        PageHelper.startPage(page, size);
        Book book = new Book();
        BeanUtils.copyProperties(searchDTO,book);

        List<Book> bookList = bookMapper.searchList(book);
        Page<Book> pages = (Page<Book>) bookList;

        PageBean pageBean = new PageBean(pages.getTotal(),pages.getResult());
        return pageBean;
    }

    @Override
    public List<Book> selectByTypeId(Integer typeId) {
        List<Book> lists = bookMapper.selectList(new QueryWrapper<Book>().eq("book_typeId",typeId));
        return lists;
    }
}
