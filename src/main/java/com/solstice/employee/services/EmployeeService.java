package com.solstice.employee.services;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.data.repositories.EmployeeRepository;
import com.solstice.employee.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee getEmployeeById(final int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No employee found for ID=" + id));
    }

    public List<Employee> getEmployeesForIds(final List<Integer> expectedIds) {
        final List<Employee> employees = new ArrayList<>();
        repository.findAllById(expectedIds).forEach(employees::add);
        return employees;
    }
}
