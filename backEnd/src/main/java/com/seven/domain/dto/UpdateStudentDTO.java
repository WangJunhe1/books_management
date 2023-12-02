package com.seven.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author :Wjh
 * @since :2023/12/2 14:41
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateStudentDTO {
    private Integer userId;

    private String studentNumber;

    private String studentName;

    private String studentSex;

    private String region;

    private String description;

    private LocalDateTime birthday;
}
