package com.pengzhaopeng.springbootjsp.mapper;

import com.pengzhaopeng.springbootjsp.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DepartmentDao {

    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    int updateDept(Department department0);
}
