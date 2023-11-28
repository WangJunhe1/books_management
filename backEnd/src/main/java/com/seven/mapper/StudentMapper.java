package com.seven.mapper;

import com.seven.domain.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper {

    /**
     * 插入学生通过姓名和学生学号
     * @param student_name
     * @param student_number
     */
    @Insert("insert into student(student_name, student_number) values (#{student_name}, #{student_number})")
    public int insertStudent(@Param("student_name") String student_name,
                              @Param("student_number") String student_number);
    @Select("select * from student where student_name = #{student_name};")
    public Student selectByStudentName(@Param("student_name") String student_name);
}
