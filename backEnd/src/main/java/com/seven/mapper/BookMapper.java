package com.seven.mapper;

import com.seven.domain.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author wjh
 * @since 2023-12-04
 */
public interface BookMapper extends BaseMapper<Book> {
    List<Book> searchList(Book book);
}
