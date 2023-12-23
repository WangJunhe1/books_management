package com.seven.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author :Wjh
 * @since :2023/12/23 18:44
 */

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime borrowStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime borrowEndTime;
}
