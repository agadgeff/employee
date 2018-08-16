package com.solstice.employee;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.data.repository.EmployeeRepository;
import com.solstice.employee.exceptions.ResourceNotFoundException;
import com.solstice.employee.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private static final List<Employee> EXPECTED_EMPLOYEES = Arrays.asList(
            new Employee(111, "John", "Smith", 12345,
                    "Chicago", "Technical Consultant", "jsmith@solstice.com", "file:///localhost/jsmith.jpg"),
            new Employee(222, "Hello", "World", 111, "NYC", "Analyst", "whatever@solstice.com", null)
    );

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getEmployeeWithUnknownIdShouldThrowException() {
        final Employee employee = service.getEmployeeById(1);
    }

    @Test
    public void getEmployeeWithPresentIdShouldReturnEmployee() {
        when(repository.findById(111)).thenReturn(Optional.of(EXPECTED_EMPLOYEES.get(0)));

        final Employee employee = service.getEmployeeById(111);
        assertEquals(EXPECTED_EMPLOYEES.get(0), employee);
    }

    @Test
    public void getEmployeesWithPresentIdsShouldReturnMatchingEmployees() {
        final List<Integer> expectedIds = EXPECTED_EMPLOYEES.stream().map(Employee::getId).collect(Collectors.toList());
        when(repository.findAllById(expectedIds)).thenReturn(EXPECTED_EMPLOYEES);

        final List<Employee> foundEmployees = service.getEmployeesForIds(expectedIds);
        assertEquals(EXPECTED_EMPLOYEES, foundEmployees);
    }


    @Test
    // TODO
    public void getEmployeesWithOnePresentIdShouldReturnMatchingEmployee() {
//        final List<Integer> expectedIds = Collections.singletonList(EXPECTED_EMPLOYEES.get(0).getId());
//        when(repository.findAllById(expectedIds)).thenReturn(EXPECTED_EMPLOYEES);
//
//        final List<Employee> foundEmployees = service.getEmployeesForIds(expectedIds);
//        assertEquals(EXPECTED_EMPLOYEES, foundEmployees);
    }
}
