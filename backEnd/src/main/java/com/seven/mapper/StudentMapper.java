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
     * @param studentName
     * @param studentNumber
     */
    @Insert("insert into student(student_name, student_number) values (#{studentName}, #{studentNumber})")
    public int insertStudent(@Param("student_name") String studentName,
                              @Param("student_number") String studentNumber);
    @Select("select * from student where student_name = #{studentName};")
    public Student selectByStudentName(@Param("student_name") String studentName);
}
