package com.bookManage.domain.vo;

import com.bookManage.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectCommentVO {
    private String commentContent;

    private String userName;

    private String portrait;

    private int commentStatus;

    private Book book;
}
