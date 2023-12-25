package com.seven.service;

import com.seven.domain.dto.SearchDTO;
import com.seven.domain.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.domain.pojo.PageBean;

import java.util.List;

/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 * @author wjh
 * @since 2023-12-04
 */
public interface BookService extends IService<Book> {

    List<Book> searchPage(SearchDTO searchDTO);

    List<Book> selectByTypeId(Integer typeId);

    PageBean nextPage(Integer currentPage);
}
