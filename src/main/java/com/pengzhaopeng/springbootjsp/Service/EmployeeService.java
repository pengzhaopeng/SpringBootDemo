package com.pengzhaopeng.springbootjsp.Service;

import com.pengzhaopeng.springbootjsp.bean.Employee;
import com.pengzhaopeng.springbootjsp.mapper.EmployeeDao;
import com.pengzhaopeng.springbootjsp.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

//    @Cacheable(cacheNames = {"emp"}, condition = "#id>0")
    public Employee getEmpById(Integer id) {
        Employee empById = employeeDao.getEmpById(id);

        //测试存放在redis里面
        if (empById != null) {
            RedisUtil.set("emp001", empById);
        }
        return empById;
    }
}
