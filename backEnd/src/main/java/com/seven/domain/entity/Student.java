package com.seven.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer student_id;
    private String student_name;
    private String student_sex;
    private String student_number;
    private Integer role_id;
    private Integer user_id;
}
