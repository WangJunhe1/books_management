package com.seven.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/12/9 11:25
 */

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
