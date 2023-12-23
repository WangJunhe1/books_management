package com.seven.domain.entity;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
public class Comment {
    private int commentId;
    private String commentContent;
    private String userId;
    private LocalDateTime commentTime;
    private String bookId;
    private String commentStatus;
}
