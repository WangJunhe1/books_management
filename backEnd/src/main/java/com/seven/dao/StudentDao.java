package com.seven.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {

    /**
     * 插入学生通过姓名和学生学号
     * @param student_name
     * @param student_number
     */
    @Insert("insert into student(student_name, student_number) values (#{student_name}, #{student_number})")
    public void insertStudent(@Param("student_name") String student_name,
                              @Param("student_number") String student_number);

}
