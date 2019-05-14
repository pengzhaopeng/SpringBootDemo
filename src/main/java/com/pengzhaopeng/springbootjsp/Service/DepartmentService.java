package com.pengzhaopeng.springbootjsp.Service;

import com.pengzhaopeng.springbootjsp.bean.Department;
import com.pengzhaopeng.springbootjsp.mapper.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public Department getDepartment(Integer id) {
        return departmentDao.getDeptById(id);
    }
}
