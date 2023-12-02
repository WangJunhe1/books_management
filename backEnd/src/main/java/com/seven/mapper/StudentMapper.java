package com.seven.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.domain.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 通过学生姓名查找学生id
     * @param studentName
     * @return
     */
    @Select("select student_id from student where student_name = #{studentName}")
    Integer selectIdByName(@Param("studentName") String studentName);

}
