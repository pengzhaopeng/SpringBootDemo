package com.pengzhaopeng.springbootjsp.controller;

import com.pengzhaopeng.springbootjsp.bean.Department;
import com.pengzhaopeng.springbootjsp.mapper.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {

    @Autowired
    DepartmentDao departmentDao;

    @ResponseBody
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return departmentDao.getDeptById(id);
    }
}
