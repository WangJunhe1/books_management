package com.bookManage.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyBorrowVO {
    private Integer bookId;

    private String bookName;

    private String bookType;

    private String bookAuthor;

    private String bookPublish;

    private String bookDesc;

    private Integer bookNumber;

    private String bookImg;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime borrowStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime borrowEndTime;
}
