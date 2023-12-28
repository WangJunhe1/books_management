package com.bookManage.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {
    @TableId(value = "comment_id", type = IdType.AUTO)
    private int commentId;

    private String commentContent;

    private Integer userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime commentTime;

    private Integer bookId;

    private Integer commentStatus;
}
