package com.seven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.domain.entity.BookType;
import com.seven.domain.vo.BookTypeVO;
import com.seven.mapper.BookTypeMapper;
import com.seven.service.BookTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjh
 * @since 2023-12-02
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
            bookTypeVO.setBookType(bookType.getBookType());
            bookTypeVO.setBookTypeLocation(bookType.getBookTypeLocation());
            return bookTypeVO;
        }).toList();

        return bookTypeVOList;
    }
}
