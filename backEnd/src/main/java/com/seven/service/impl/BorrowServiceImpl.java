package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.seven.domain.dto.BorrowDTO;
import com.seven.domain.entity.Borrow;
import com.seven.mapper.BookMapper;
import com.seven.mapper.BorrowMapper;
import com.seven.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjh
 * @since 2023-12-22
 */
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void borrowBook(BorrowDTO borrowDTO) {

        LambdaQueryWrapper<Borrow> queryWrapper = new LambdaQueryWrapper<Borrow>()
                .eq(Borrow::getBookId, borrowDTO.getBookId())
                .eq(Borrow::getBorrowStudentNumber, borrowDTO.getBorrowStudentNumber());

        Borrow borrowtemp = borrowMapper.selectOne(queryWrapper);
        if (bookMapper.selectById(borrowDTO.getBookId()) == null) {
            throw new RuntimeException("图书不存在");
        }
        if(borrowtemp != null && borrowtemp.getBorrowStatus() == 1){
            throw new RuntimeException("图书已借阅");
        }

        Borrow borrow = new Borrow().builder()
                .bookId(borrowDTO.getBookId())
                .borrowStudentNumber(borrowDTO.getBorrowStudentNumber())
                .borrowStartTime(LocalDateTime.now())
               .borrowEndTime(LocalDateTime.now().plusMonths(2))
               .borrowStatus(1)
                .build();

        borrowMapper.insert(borrow);
    }
}
