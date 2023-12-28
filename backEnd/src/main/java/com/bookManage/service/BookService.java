package com.bookManage.service;

import com.bookManage.domain.dto.SearchDTO;
import com.bookManage.domain.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bookManage.domain.pojo.PageBean;

import java.util.List;

/**
 * <p>
 * 图书表 服务类
 * </p>
 */
public interface BookService extends IService<Book> {

    List<Book> searchPage(SearchDTO searchDTO);

    List<Book> selectByTypeId(Integer typeId);

    PageBean nextPage(Integer currentPage);
}
