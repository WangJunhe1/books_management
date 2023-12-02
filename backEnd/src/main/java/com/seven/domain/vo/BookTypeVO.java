package com.seven.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/12/2 12:07
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTypeVO {

    private String bookType;

    private String bookTypeLocation;

}
