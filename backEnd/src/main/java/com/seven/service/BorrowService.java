package com.seven.service;

import com.seven.domain.dto.BorrowDTO;
import com.seven.domain.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
