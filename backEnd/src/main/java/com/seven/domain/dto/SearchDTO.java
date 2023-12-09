package com.seven.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/12/9 11:25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    private String bookName;

    private String bookAuthor;

    private String bookDesc;
}
