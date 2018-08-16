package com.solstice.employee.controllers;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) { //Add validation later...
        return service.getEmployeeById(id);
    }
}
