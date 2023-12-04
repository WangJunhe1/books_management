package com.seven.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student {
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    private String studentName;

    private String studentSex;

    private String studentNumber;

    private LocalDateTime birthday = null;

    private String studentEmail;

    private String region;

    private String description = null;

    private Integer roleId;

    private Integer userId;
}
