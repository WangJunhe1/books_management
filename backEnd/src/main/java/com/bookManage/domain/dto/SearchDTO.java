package com.bookManage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    @Builder.Default
    private String bookName = null;

    @Builder.Default
    private String bookAuthor =null ;

    @Builder.Default
    private String bookDesc = null;
}
