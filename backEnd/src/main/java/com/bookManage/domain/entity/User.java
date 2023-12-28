package com.bookManage.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String password;

    private String phone;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    private Integer studentId;
}
