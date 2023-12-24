package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.dto.BookIdCommentDTO;
import com.seven.domain.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIdCommentDTOMapper extends BaseMapper<BookIdCommentDTO> {
}
