package com.bookManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bookManage.constant.BorrowConstant;
import com.bookManage.domain.dto.BorrowDTO;
import com.bookManage.domain.entity.Borrow;
import com.bookManage.domain.vo.MyBorrowVO;
import com.bookManage.mapper.BookMapper;
import com.bookManage.mapper.BorrowMapper;
import com.bookManage.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
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
            throw new RuntimeException(BorrowConstant.BOOK_NOT_EXIST);
        }
        if(borrowtemp != null && borrowtemp.getBorrowStatus() == 1){
            throw new RuntimeException(BorrowConstant.BOOK_IS_BORROWED);
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

    @Override
    public List<MyBorrowVO> getMyBorrow(String studentNumber) {

        List<MyBorrowVO> bookList = borrowMapper.selectMyBorrow(studentNumber);
        System.out.println(bookList);

        return bookList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void returnBorrow(BorrowDTO borrowDTO) {
        LambdaQueryWrapper<Borrow> queryWrapper = new LambdaQueryWrapper<Borrow>()
                .eq(Borrow::getBookId, borrowDTO.getBookId())
                .eq(Borrow::getBorrowStudentNumber, borrowDTO.getBorrowStudentNumber())
                .eq(Borrow::getBorrowStatus, 1);

        Borrow borrowtemp = borrowMapper.selectOne(queryWrapper);
        if (bookMapper.selectById(borrowDTO.getBookId()) == null) {
            throw new RuntimeException(BorrowConstant.BOOK_NOT_EXIST);
        }
        else if(borrowtemp == null){
            throw new RuntimeException(BorrowConstant.BOOK_IS_NOT_BORROWED);
        }

        Borrow borrow = new Borrow().builder()
                .borrowStatus(2)
                .build();

        borrowMapper.update(borrow, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void renewalBorrow(BorrowDTO borrowDTO) {
        LambdaQueryWrapper<Borrow> queryWrapper = new LambdaQueryWrapper<Borrow>()
                .eq(Borrow::getBookId, borrowDTO.getBookId())
                .eq(Borrow::getBorrowStudentNumber, borrowDTO.getBorrowStudentNumber())
                .eq(Borrow::getBorrowStatus, 1);

        Borrow borrowtemp = borrowMapper.selectOne(queryWrapper);
        if (bookMapper.selectById(borrowDTO.getBookId()) == null) {
            throw new RuntimeException(BorrowConstant.BOOK_NOT_EXIST);
        }
        else if(borrowtemp == null){
            throw new RuntimeException(BorrowConstant.BOOK_IS_NOT_BORROWED);
        }

        Borrow borrow = new Borrow().builder()
                .borrowEndTime(borrowtemp.getBorrowEndTime().plusMonths(1))
                .build();

        borrowMapper.update(borrow, queryWrapper);
    }
}
