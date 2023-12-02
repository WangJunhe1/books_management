package com.seven.service;

import com.seven.domain.entity.BookType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.domain.vo.BookTypeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjh
 * @since 2023-12-02
 */
public interface BookTypeService extends IService<BookType> {
    List<BookTypeVO> getBookTypeList();
}
