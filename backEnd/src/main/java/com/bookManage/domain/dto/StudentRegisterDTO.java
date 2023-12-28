package com.bookManage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRegisterDTO {

    private String studentName;

    private String studentNumber;

    private String studentSex;

    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday = null;

    private String studentEmail;

    @Builder.Default
    private String region = null;

    @Builder.Default
    private String description = null;

    @Builder.Default
    private Integer roleId = 2;
}
