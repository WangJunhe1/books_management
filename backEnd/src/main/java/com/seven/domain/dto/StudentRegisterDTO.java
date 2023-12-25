package com.seven.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday = null;

    private String studentEmail;

    @Builder.Default
    private String region = null;

    @Builder.Default
    private String description = null;

    @Builder.Default
    private Integer roleId = 2;
}
