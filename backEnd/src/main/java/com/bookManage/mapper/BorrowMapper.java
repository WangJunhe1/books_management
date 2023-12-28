package com.bookManage.mapper;

import com.bookManage.domain.entity.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookManage.domain.vo.MyBorrowVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface BorrowMapper extends BaseMapper<Borrow> {
    List<MyBorrowVO> selectMyBorrow(String studentNumber);
}
