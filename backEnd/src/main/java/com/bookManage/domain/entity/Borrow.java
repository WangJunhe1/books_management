package com.bookManage.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("borrow")
public class Borrow {

    @TableId(value = "borrow_id", type = IdType.AUTO)
    private Integer borrowId;

    private Integer bookId;

    private String borrowStudentNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime borrowStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime borrowEndTime;

    //1为借阅中，2为已归还
    private Integer borrowStatus;
}
