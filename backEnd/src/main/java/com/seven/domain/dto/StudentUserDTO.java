package com.seven.domain.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/12/2 16:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUserDTO {
    private Integer userId;

    private String studentName;
}
