package com.seven.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/12/22 15:56
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDTO {
    private Integer bookId;
    private String borrowStudentNumber;
}
