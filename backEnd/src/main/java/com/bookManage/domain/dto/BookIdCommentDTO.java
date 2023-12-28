package com.bookManage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookIdCommentDTO {
    private Integer bookId;

    private String commentContent;

    private Integer commentStatus;

    @Builder.Default
    private Integer userId = null;
}
