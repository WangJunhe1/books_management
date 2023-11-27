package com.seven.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer user_id;
    private String username;
    private String password;
    private String phone;
    private Integer student_id;
}
