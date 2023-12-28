package com.bookManage.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTypeVO {

    private Integer bookTypeId;

    private String bookType;

    private String bookTypeLocation;

    private Integer bookNumber;

}
