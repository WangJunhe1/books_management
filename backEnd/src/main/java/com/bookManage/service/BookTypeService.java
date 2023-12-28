package com.bookManage.service;

import com.bookManage.domain.entity.BookType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bookManage.domain.vo.BookTypeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface BookTypeService extends IService<BookType> {
    List<BookTypeVO> getBookTypeList();
}
