package com.pengzhaopeng.springbootjsp.mapper;

import com.pengzhaopeng.springbootjsp.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * 员工表
 */
@Component
@Mapper
public interface EmployeeDao {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    void deleteEmpById(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    void insertEmployee(Employee employee);
}
