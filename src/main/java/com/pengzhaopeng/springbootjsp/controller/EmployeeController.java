package com.pengzhaopeng.springbootjsp.controller;

import com.pengzhaopeng.springbootjsp.Service.EmployeeService;
import com.pengzhaopeng.springbootjsp.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmpById(id);
    }
}
