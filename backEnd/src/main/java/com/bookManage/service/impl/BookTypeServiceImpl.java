package com.bookManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookManage.domain.entity.BookType;
import com.bookManage.domain.vo.BookTypeVO;
import com.bookManage.mapper.BookTypeMapper;
import com.bookManage.service.BookTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
@Slf4j
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public List<BookTypeVO> getBookTypeList() {
        List<BookType> bookTypes = bookTypeMapper.selectList(new QueryWrapper<>());
        log.info("bookTypes: {}", bookTypes);

        List<BookTypeVO> bookTypeVOList = bookTypes.stream().map(bookType -> {
            BookTypeVO bookTypeVO = new BookTypeVO();
            bookTypeVO.setBookTypeId(bookType.getBookTypeId());
            bookTypeVO.setBookType(bookType.getBookType());
            bookTypeVO.setBookTypeLocation(bookType.getBookTypeLocation());

            bookTypeVO.setBookNumber(bookType.getBookNumber());
            return bookTypeVO;
        }).toList();

        return bookTypeVOList;
    }
}
