package com.seven.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author :Wjh
 * @since :2023/12/24 16:19
 */

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
