package com.bookManage.service;

import com.bookManage.domain.dto.BorrowDTO;
import com.bookManage.domain.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bookManage.domain.vo.MyBorrowVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface BorrowService extends IService<Borrow> {
    void borrowBook(BorrowDTO borrowDTO);

    List<MyBorrowVO> getMyBorrow(String studentNumber);

    void returnBorrow(BorrowDTO borrowDTO);

    void renewalBorrow(BorrowDTO borrowDTO);
}
