package com.solstice.employee.data.repositories;

import com.solstice.employee.data.entities.Employee;
import com.solstice.employee.data.repositories.EmployeeRepository;
import org.assertj.core.util.IterableUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    private static final List<Employee> EXPECTED_EMPLOYEES = Arrays.asList(
            new Employee(111, "John", "Smith", 12345,
                    "Chicago", "Technical Consultant", "jsmith@solstice.com", "file:///localhost/jsmith.jpg"),
            new Employee(222, "Hello", "World", 111, "NYC", "Analyst", "whatever@solstice.com", null)
    );

    @Autowired
    private EmployeeRepository repository;
    private List<Employee> employees;

    @Before
    public void setUp() {
        employees  = new ArrayList<>();
        repository.saveAll(EXPECTED_EMPLOYEES).forEach(employees::add);
    }

    @Test
    public void findWithValidIdShouldReturnEmployee() {
        final Optional<Employee> match = repository.findById(employees.get(0).getId());

        assertTrue(match.isPresent());
        final Employee employee = match.get();
        assertEquals(employees.get(0), employee);
    }

    @Test
    public void findWithInvalidIdShouldReturnEmpty() {
        final Optional<Employee> match = repository.findById(0);

        assertFalse(match.isPresent());
    }

    @Test
    public void findAllByIdsShouldReturnAllMatching() {
        final Iterable<Employee> matches = repository.findAllById(employees.stream().map(Employee::getId).collect(Collectors.toList()));

        final Collection actualEmployees = IterableUtil.toCollection(matches);
        assertEquals(2, actualEmployees.size());
        assertTrue(actualEmployees.contains(employees.get(0)));
        assertTrue(actualEmployees.contains(employees.get(1)));
    }

    @Test
    public void findAllByIdsWithSomeMatchingShouldReturnOnlyMatching() {
        List<Integer> searchList = Arrays.asList(employees.get(0).getId(),Integer.MAX_VALUE);

        final Iterable<Employee> matches = repository.findAllById(searchList);

        final Collection actualEmployees = IterableUtil.toCollection(matches);

        assertEquals(1, actualEmployees.size());
        assertTrue(actualEmployees.contains(employees.get(0)));
    }
}
