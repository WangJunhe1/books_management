package com.seven.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author :Wjh
 * @since :2023/11/30 11:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRegisterDTO {

    private String studentName;

    private String studentNumber;

    private String studentSex;

    @Builder.Default
    private LocalDateTime birthday = null;

    private String studentEmail;

    @Builder.Default
    private String region = null;

    @Builder.Default
    private String description = null;

    @Builder.Default
    private Integer roleId = 2;
}
