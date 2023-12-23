package com.seven.mapper;

import com.seven.domain.entity.Book;
import com.seven.domain.entity.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.vo.MyBorrowVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjh
 * @since 2023-12-22
 */
public interface BorrowMapper extends BaseMapper<Borrow> {
    List<MyBorrowVO> selectMyBorrow(String studentNumber);
}
