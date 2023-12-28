package com.bookManage.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCommentVO {

    private Integer bookId;

    private String bookName;

    private String commentContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime commentTime;

    private Integer commentStatus;

}
