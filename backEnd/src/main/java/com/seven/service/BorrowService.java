package com.seven.service;

import com.seven.domain.dto.BorrowDTO;
import com.seven.domain.entity.Book;
import com.seven.domain.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seven.domain.vo.MyBorrowVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjh
 * @since 2023-12-22
 */
public interface BorrowService extends IService<Borrow> {
    void borrowBook(BorrowDTO borrowDTO);

    List<MyBorrowVO> getMyBorrow(String studentNumber);

    void returnBorrow(BorrowDTO borrowDTO);

    void renewalBorrow(BorrowDTO borrowDTO);
}
