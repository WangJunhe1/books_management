package com.bookManage.mapper;

import com.bookManage.domain.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 */
public interface BookMapper extends BaseMapper<Book> {
    List<Book> searchList(String key);
}
