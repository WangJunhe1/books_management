package com.bookManage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookManage.domain.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 通过学生姓名查找学生id
     * @param studentNumber
     * @return
     */
    @Select("select student_id from student where student_number = #{studentNumber}")
    Integer selectIdByNumber(@Param("studentNumber") String studentNumber);

}
